package web.template

import kotlinx.html.*
import lightnovelApi.model.response.ArticleResponse
import org.jsoup.Jsoup
import web.model.SimplePageInfo
import web.pager
import web.toSimple
import java.net.URLEncoder
import java.nio.charset.Charset
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun HTML.articlePage(
    detail: ArticleResponse,
    seriesInfo: SimplePageInfo? = null,
    pageInfo: SimplePageInfo? = null
) {
    commonPage(detail.title.toSimple()) {
        div("article-header") {
            if (detail.sid == 0) {
                +"查看合集"
            } else {
                a("/series/${detail.sid}") { +"查看合集" }
            }
        }
        div("time") {
            +"时间：${LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))}"
        }
        div("page-header") {
            pageInfo?.let {
                if ((it.cur == 1) or (it.count == 1)) img(
                    detail.title,
                    "/res?url=${URLEncoder.encode(detail.cover, Charset.defaultCharset())}"
                )
            }
            br()
            a("?fullpage=true") { +"全文模式" }
            seriesInfoToHTML(seriesInfo)
            br()
            pageInfoToHTML(pageInfo)
        }
        br()
        var content = detail.content.toSimple()
        val doc = Jsoup.parseBodyFragment(content)
        val imgs = doc.getElementsByTag("img")
        imgs.forEach {
            val src = it.attr("src")
            if (it.attr("src").startsWith("https://res.lightnovel.us")) {
                it.attr("src", "/res?url=${URLEncoder.encode(src, Charset.defaultCharset())}")
            }
        }
        content = doc.outerHtml()
        if (pageInfo != null) {
            content = pager(content)[pageInfo.cur - 1]
        }
        main("content") { unsafe { +content.replace("\n", "<br>") } }
        div("page-footer") {
            pageInfoToHTML(pageInfo)
            br()
            seriesInfoToHTML(seriesInfo)
        }
    }
}

fun FlowOrPhrasingContent.seriesInfoToHTML(info: SimplePageInfo?) {
    info?.let {
        br()
        if (it.has_next) {
            a("/article/${it.next}") { +"下章" }
        }
        +" "
        if (it.has_prev) {
            a("/article/${it.prev}") { +"上章" }
        }
        br()
    }
}

fun FlowOrPhrasingContent.pageInfoToHTML(info: SimplePageInfo?) {
    info?.let {
        if (it.has_next) {
            a("?page=${it.next}") { +"下一页" }
            +" "
        }
        if (it.has_prev) {
            a("?page=${it.prev}") { +"上一页" }
        }
        +"[${it.cur}/${it.count}]"
    }
}