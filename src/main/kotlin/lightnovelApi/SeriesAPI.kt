package lightnovelApi

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import lightnovelApi.model.request.Request
import lightnovelApi.model.request.SeriesInfoRequest
import lightnovelApi.model.response.Response
import lightnovelApi.model.response.SeriesInfoResponse

object SeriesAPI : BasicApi() {
    suspend fun getInfo(sid: Int, security_key: String = ""): SeriesInfoResponse {
        val apiPath = "/api/series/get-info"
        val response: Response<SeriesInfoResponse> = post {
            url.path(apiPath)
            setBody(
                Request(d = SeriesInfoRequest(sid, security_key))
            )
        }.body()
        return response.data
    }
}