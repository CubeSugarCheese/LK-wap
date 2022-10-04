package lightnovelApi

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

sealed class BasicApi {
    protected val client: HttpClient = HttpClient(CIO) {
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
}
