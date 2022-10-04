package web

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.body
import kotlinx.html.p
import lightnovelApi.CategoryAPI
import lightnovelApi.SeriesAPI
import web.cache.ArticleCache
import web.cache.SeriesCache
import web.model.SimplePageInfo
import web.template.*


fun Routing.root() {
    get("/") {
        val page = call.request.queryParameters["page"]?.toInt() ?: 1
        val category = CategoryAPI.getCategories(parent_gid = 3, Config.loadFormFileOrDefault().security_key)
        val articles = CategoryAPI.getArticleByCate(
            parent_gid = 3,
            gid = 106,
            page = page,
            pageSize = Config.loadFormFileOrDefault().pageSize,
            security_key = Config.loadFormFileOrDefault().security_key
        )
        call.respondHtml { homePage(category, articles, page) }
    }
}

fun Routing.category() {
    get("/category/{gid}") {
        val gid = call.parameters["gid"]!!.toInt()
        val page = call.request.queryParameters["page"]?.toInt() ?: 1
        val categories = CategoryAPI.getCategories(3, Config.loadFormFileOrDefault().security_key)
        val detail = categories.find { it.gid == gid }
        if (detail == null) {
            call.respondText(text = "未找到该分区！")
        }
        val articles = CategoryAPI.getArticleByCate(
            3,
            gid,
            page,
            Config.loadFormFileOrDefault().pageSize,
            Config.loadFormFileOrDefault().security_key
        )
        call.respondHtml {
            categoryPage(detail = detail!!, articles = articles, categories = categories, page = page)
        }
    }
}

fun Routing.article() {
    get("/article/{aid}") {
        val aid = call.parameters["aid"]!!.toInt()
        var page = call.request.queryParameters["page"]?.toInt() ?: 1
        if (page < 1) {
            page = 1
        }
        val fullpage = call.request.queryParameters["fullpage"]?.toBoolean() ?: false
        val article = ArticleCache.getAndUpdate(aid)

        if (article.sid != 0) {
            var seriesInfo = SeriesCache.getAndUpdate(article.sid)
            if (seriesInfo.articles.none { it.aid == aid }) {
                // 若是缓存里获取到的合集信息没有对应的文章，说明缓存需要更新
                seriesInfo = SeriesAPI.getInfo(article.sid)
                SeriesCache.insertOrUpdate(seriesInfo)
            }

            // 以下是魔法
            val cur = seriesInfo.articles.findIndex { it.aid == aid }!!
            val s_next = if (cur + 1 == seriesInfo.articles.size) {
                seriesInfo.articles.last().aid
            } else {
                seriesInfo.articles[cur + 1].aid
            }
            val s_prev = if (cur == 0) seriesInfo.articles.first().aid else seriesInfo.articles[cur - 1].aid
            val has_next = (aid != s_next)
            val has_prev = (aid != s_prev)
            val seriesPageInfo = SimplePageInfo(seriesInfo.articles.size, cur, s_prev, s_next, has_prev, has_next)
            // 魔法结束

            var pageInfo: SimplePageInfo? = null
            if (!fullpage) {
                val pages = pager(article.content)
                pageInfo = SimplePageInfo(pages.size, page)
            }
            call.respondHtml {
                articlePage(article, seriesPageInfo, pageInfo)
            }
        } else {
            var pageInfo: SimplePageInfo? = null
            if (!fullpage) {
                val pages = pager(article.content)
                pageInfo = SimplePageInfo(pages.size, page)
            }
            call.respondHtml {
                articlePage(article, pageInfo = pageInfo)
            }
        }
    }
}

fun Routing.series() {
    get("/series/{sid}") {
        val sid = call.parameters["sid"]!!.toInt()
        val series = SeriesCache.getAndUpdate(sid)
        call.respondHtml {
            seriesPage(series)
        }
    }
}

fun Routing.resProxy() {
    get("/res") {
        val url = call.request.queryParameters["url"]
        if (url == null) {
            call.respondText("There is lk res image proxy")
        } else {
            val response = HttpClient(CIO).get(url) {
                headers {
                    append(HttpHeaders.Referrer, "https://www.lightnovel.us/")
                }
            }
            val image: ByteArray = response.body()
            call.respondBytes(image, ContentType.Image.Any)
        }
    }
}

fun Routing.cache() {
    get("/cache") {
        call.respondHtml {
            cachePage(ArticleCache.getAllArticle())
        }
    }
}

fun Routing.test() {
    get("/test") {
        ArticleCache.get(1)
        call.respondHtml {
            body {
                p { +"Headers: ${call.request.headers.format()}" }
                p { +"Body: ${call.request.queryParameters.format()}" }
            }
        }
    }
}
