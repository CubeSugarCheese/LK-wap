package web.template

import kotlinx.html.BODY
import kotlinx.html.a
import kotlinx.html.br
import kotlinx.html.div

fun BODY.header() {
    div("header") {
        a("/") { +"首页" }
    }
}

fun BODY.footer() {
    br()
    text("由 ktor 强力驱动 / 数据来源：轻之国度 / 灵感来源: Wenku8")
}
