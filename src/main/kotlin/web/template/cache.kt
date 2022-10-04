package web.template

import kotlinx.html.*
import lightnovelApi.model.response.ArticleResponse
import web.toSimple

fun HTML.cachePage(articles: List<ArticleResponse?>) {
    commonPage("缓存页面-轻之国度") {
        div("cached-articles") {
            for (i in articles) {
                i?.let {
                    a("/article/${i.aid}") { +i.title.toSimple() }
                    br()
                }
            }
        }
    }
}