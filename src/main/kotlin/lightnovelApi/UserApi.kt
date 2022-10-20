package lightnovelApi

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import lightnovelApi.model.request.LoginRequest
import lightnovelApi.model.request.Request
import lightnovelApi.model.response.LoginResponse
import lightnovelApi.model.response.Response

object UserApi : BasicApi() {
    suspend fun login(username: String, password: String): LoginResponse {
        val apiPath = "/api/user/login"
        val response: Response<LoginResponse>  = post {
            url.path(apiPath)
            setBody(
                Request(d = LoginRequest(username, password))
            )
        }.body()
        return response.data
    }
}