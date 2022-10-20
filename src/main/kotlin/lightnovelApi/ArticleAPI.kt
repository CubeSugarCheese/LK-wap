package lightnovelApi

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import lightnovelApi.model.request.ArticleRequest
import lightnovelApi.model.request.Request
import lightnovelApi.model.response.ArticleResponse
import lightnovelApi.model.response.Response

object ArticleAPI : BasicApi() {
    suspend fun getDetail(aid: Int, security_key: String = ""): ArticleResponse {
        val apiPath = "/api/article/get-detail"
        val response: Response<ArticleResponse> = post {
            url.path(apiPath)
            setBody(
                Request(d = ArticleRequest(aid = aid, security_key = security_key))
            )
        }.body()
        return response.data
    }
}