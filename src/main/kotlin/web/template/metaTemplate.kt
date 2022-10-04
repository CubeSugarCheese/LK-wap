package web.template

import kotlinx.html.*

inline fun HTML.commonPage(
    title: String,
    crossinline page: BODY.() -> Unit
) {
    head {
        lang = "zh"
        title(title)
        meta {
            name = "referrer"
            content = "no-referrer"
        }
    }
    body {
        header()
        page()
        footer()
    }
}