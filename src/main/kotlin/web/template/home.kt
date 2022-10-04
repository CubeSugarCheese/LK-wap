package web.template

import kotlinx.html.*
import lightnovelApi.model.common.Categories
import lightnovelApi.model.response.ArticleByCateResponse
import web.toSimple

fun HTML.homePage(category: List<Categories>, articles: ArticleByCateResponse, page: Int) {
    commonPage("轻小说版-轻之国度") {
        div("sub-category") {
            for (i in category) {
                a("/category/${i.gid}") { +i.name.toSimple() }
                +"."
            }
        }
        if (page > 1) {
            a("?page=${page - 1}") { +"上一页" }
            +"|"
        }
        a("?page=${page + 1}") { +"下一页" }
        br()
        div("newest-articles") {
            for (i in articles.list) {
                a("/article/${i.aid}") { +i.title.toSimple() }
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
