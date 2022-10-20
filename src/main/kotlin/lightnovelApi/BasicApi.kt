package lightnovelApi

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import lightnovelApi.exception.LKApiResponseException

sealed class BasicApi {
    private val client: HttpClient = HttpClient(CIO) {
        expectSuccess = true
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url {
                host = "api.lightnovel.us"
                protocol = URLProtocol.HTTPS
            }
            headers {
                contentType(ContentType.Application.Json)
            }
        }
    }

    /**
     * @throws LKApiResponseException
     * @throws ResponseException
     **/
    suspend fun post(block: HttpRequestBuilder.() -> Unit): HttpResponse {
        val response = client.post(HttpRequestBuilder().apply(block))
        val text = response.bodyAsText()
        val element = Json.parseToJsonElement(text)
        val code: Int = element.jsonObject["code"]!!.jsonPrimitive.int
        if (code != 0) {
            throw LKApiResponseException(code)
        }
        return response
    }
}
