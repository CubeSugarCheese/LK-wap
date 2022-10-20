package lightnovelApi

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import lightnovelApi.model.common.ArticleCates
import lightnovelApi.model.common.Categories
import lightnovelApi.model.request.ArticleByCateRequest
import lightnovelApi.model.request.ArticleCatesRequest
import lightnovelApi.model.request.CategoriesRequest
import lightnovelApi.model.request.Request
import lightnovelApi.model.response.ArticleByCateResponse
import lightnovelApi.model.response.Response

object CategoryAPI: BasicApi() {
    suspend fun getArticleCates(depth: Int, cache: Boolean, security_key: String=""): List<ArticleCates> {
        val apiPath = "/api/category/get-article-cates"
        val response: Response<List<ArticleCates>> = post {
            url.path(apiPath)
            setBody(
                Request(d = ArticleCatesRequest(depth, cache, security_key))
            )
        }.body()
        return response.data
    }

    suspend fun getCategories(parent_gid: Int, security_key: String=""): List<Categories> {
        val apiPath = "/api/category/get-categories"
        val response: Response<List<Categories>> = post {
            url.path(apiPath)
            setBody(
                Request(d = CategoriesRequest(parent_gid, security_key))
            )
        }.body()
        return response.data
    }

    suspend fun getArticleByCate(parent_gid: Int, gid: Int, page: Int, pageSize: Int? = null, security_key: String=""): ArticleByCateResponse {
        val apiPath = "/api/category/get-article-by-cate"
        val response: Response<ArticleByCateResponse> = post {
            url.path(apiPath)
            setBody(
                Request(d = ArticleByCateRequest(parent_gid, gid, page, pageSize, security_key))
            )
        }.body()
        return response.data
    }
}