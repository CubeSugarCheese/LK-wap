import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import web.*
import web.Config.Companion.loadFormFileOrDefault
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

