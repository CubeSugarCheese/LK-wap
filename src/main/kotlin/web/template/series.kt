package web.template

import kotlinx.html.*
import lightnovelApi.model.response.SeriesInfoResponse
import web.toSimple
import java.net.URLEncoder
import java.nio.charset.Charset

fun HTML.seriesPage(info: SeriesInfoResponse) {
    commonPage(info.name) {
        b { +(info.name.toSimple()) }
        br()
        img(info.name.toSimple(),"/res?url=${URLEncoder.encode(info.cover, Charset.defaultCharset())}")
        br()
        +"作者: ${info.author}"
        br()
        +"[简介]"
        br()
        unsafe { +info.intro.replace("\n", "<br>") }
        br()


        for (i in info.articles) {
            +"◇"
            a("/article/${ i.aid }") { unsafe { +i.title.toSimple() } }
            br()
        }
    }
}
