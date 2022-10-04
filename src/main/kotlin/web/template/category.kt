package web.template

import kotlinx.html.*
import lightnovelApi.model.common.Categories
import lightnovelApi.model.response.ArticleByCateResponse
import web.toSimple

fun HTML.categoryPage(detail: Categories, articles: ArticleByCateResponse, categories: List<Categories>, page: Int) {
    commonPage("${detail.name.toSimple()}-轻之国度") {
        div("sub-category") {
            for (i in categories) {
                a("/category/${i.gid}") { +i.name.toSimple() }
                +"."
            }
        }
        br()
        if (page > 1) {
            a("?page=${page - 1}") { +"上一页" }
            +"|"
        }
        a("?page=${page + 1}") { +"下一页" }
        div("newest-articles") {
            for (i in articles.list) {
                a("/article/${ i.aid }") { +i.title.toSimple() }
                br()
            }
        }
        if (page > 1) {
            a("?page=${page - 1}") { +"上一页" }
            +"|"
        }
        a("?page=${page + 1}") { +"下一页" }
    }
}