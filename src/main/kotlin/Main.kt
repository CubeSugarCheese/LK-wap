import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import lightnovelApi.exception.LKApiResponseException
import web.*
import web.Config.Companion.loadFormFileOrDefault
import web.template.commonPage
import web.template.internalErrorPage

fun main() {
    val env = applicationEngineEnvironment {
        module {
            routing {
                root()
                category()
                article()
                series()
                resProxy()
                cache()
                test()
            }
            install(CallLogging)
            install(StatusPages) {
                exception<NumberFormatException> { call, _ ->
                    call.respondRedirect("/")
                }

                exception<LKApiResponseException> { call, e ->
                    call.respondHtml {
                        commonPage("发生错误！") { +"API返回错误!错误代码：${e.code}" }
                    }
                    call.logger.error(e.localizedMessage, e)
                }

                exception<ResponseException> { call, e ->
                    call.respondHtml {
                        commonPage("LK维护中！") { +"LK维护中！" }
                    }
                    call.logger.error(e.localizedMessage, e)
                }

                exception<Throwable> { call, e ->
                    call.respondHtml(status = HttpStatusCode.InternalServerError) {
                        internalErrorPage(e)
                    }
                    call.logger.error(e.localizedMessage, e)
                }
            }
        }

        connector {
            host = loadFormFileOrDefault().host
            port = loadFormFileOrDefault().port
        }
    }
    embeddedServer(CIO, env).start(true)
}

