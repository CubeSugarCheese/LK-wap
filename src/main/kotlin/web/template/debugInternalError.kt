package web.template

import kotlinx.html.*

fun HTML.internalErrorPage(e: Throwable) {
    head {
        lang = "en"
        title("${e.javaClass.simpleName}: ${e.message}")
    }
    body {
        style = "background-color: #fff"
        h1 { +e.javaClass.simpleName }
        div("debugger") {
            div("detail") {
                p("errormsg") { +"${e.javaClass.simpleName}: ${e.message}" }
            }
        }
        h2("traceback") {
            style = "cursor: pointer;"
            +"Traceback"
            em { +"(most recent call last)" }
        }
        div("traceback") {
            h3()
            ul {
                for (i in e.stackTrace) {
                    li {
                        div("frame") {
                            h4 {
                                +"File "
                                cite("filename") { text(i.fileName ?: "Unknown file") }
                                +", line"
                                em("line") { text(i.lineNumber) }
                                +", in "
                                code("function") { text(i.methodName) }
                            }
                            div("source library") {
                                pre("line current") { text("${i.className}: ${i.methodName}()") }
                            }
                        }
                    }
                }
            }
        }

        style {
            unsafe {
                +"root{text-align: left;};body,input{font-family:sans-serif;color:#000;text-align:center;margin:1em;padding:0;font-size:15px}h1,h2,h3{font-weight:normal}input{background-color:#fff;margin:0;text-align:left;outline:none!important}input[type=\"submit\"]{padding:3px 6px}a{color:#11557c}a:hover{color:#177199}pre,code,textarea{font-family:monospace;font-size:14px}div.debugger{text-align:left;padding:12px;margin:auto;background-color:white}h1{font-size:36px;margin:0 0 .3em 0}div.detail{cursor:pointer}div.detail p{margin:0 0 8px 13px;font-size:14px;white-space:pre-wrap;font-family:monospace}div.explanation{margin:20px 13px;font-size:15px;color:#555}div.footer{font-size:13px;text-align:right;margin:30px 0;color:#86989b}h2{font-size:16px;margin:1.3em 0 0 0;padding:9px;background-color:#11557c;color:white}h2 em,h3 em{font-style:normal;color:#a5d6d9;font-weight:normal}div.traceback,div.plain{border:1px solid #ddd;margin:0 0 1em 0;padding:10px}div.plain p{margin:0}div.plain textarea,div.plain pre{margin:10px 0 0 0;padding:4px;background-color:#e8eff0;border:1px solid #d3e7e9}div.plain textarea{width:99%;height:300px}div.traceback h3{font-size:1em;margin:0 0 .8em 0}div.traceback ul{list-style:none;margin:0;padding:0 0 0 1em}div.traceback h4{font-size:13px;font-weight:normal;margin:.7em 0 .1em 0}div.traceback pre{margin:0;padding:5px 0 3px 15px;background-color:#e8eff0;border:1px solid #d3e7e9}div.traceback .library .current{background:white;color:#555}div.traceback .expanded .current{background:#e8eff0;color:black}div.traceback pre:hover{background-color:#ddecee;color:black;cursor:pointer}div.traceback div.source.expanded pre+pre{border-top:0}div.traceback span.ws{display:none}div.traceback pre.before,div.traceback pre.after{display:none;background:white}div.traceback div.source.expanded pre.before,div.traceback div.source.expanded pre.after{display:block}div.traceback div.source.expanded span.ws{display:inline}div.traceback blockquote{margin:1em 0 0 0;padding:0;white-space:pre-line}div.traceback img{float:right;padding:2px;margin:-3px 2px 0 0;display:none}div.traceback img:hover{background-color:#ddd;cursor:pointer;border-color:#bfdde0}div.traceback pre:hover img{display:block}div.traceback cite.filename{font-style:normal;color:#3b666b}pre.console{border:1px solid #ccc;background:white!important;color:black;padding:5px!important;margin:3px 0 0 0!important;cursor:default!important;max-height:400px;overflow:auto}pre.console form{color:#555}pre.console input{background-color:transparent;color:#555;width:90%;font-family:monospace;font-size:14px;border:none!important}span.string{color:#30799b}span.number{color:#9c1a1c}span.help{color:#3a7734}span.object{color:#485f6e}span.extended{opacity:.5}span.extended:hover{opacity:1}a.toggle{text-decoration:none;background-repeat:no-repeat;background-position:center center;background-image:url(?__debugger__=yes&cmd=resource&f=more.png)}a.toggle:hover{background-color:#444}a.open{background-image:url(?__debugger__=yes&cmd=resource&f=less.png)}pre.console div.traceback,pre.console div.box{margin:5px 10px;white-space:normal;border:1px solid #11557c;padding:10px;font-family:sans-serif}pre.console div.box h3,pre.console div.traceback h3{margin:-10px -10px 10px -10px;padding:5px;background:#11557c;color:white}pre.console div.traceback pre:hover{cursor:default;background:#e8eff0}pre.console div.traceback pre.syntaxerror{background:inherit #e8eff0;border:0;margin:20px -10px -10px -10px;padding:10px;border-top:1px solid #bfdde0}pre.console div.noframe-traceback pre.syntaxerror{margin-top:-10px;border:0}pre.console div.box pre.repr{padding:0;margin:0;background-color:white;border:0}pre.console div.box table{margin-top:6px}pre.console div.box pre{border:0}pre.console div.box pre.help{background-color:white}pre.console div.box pre.help:hover{cursor:default}pre.console table tr{vertical-align:top}div.console{border:1px solid #ccc;padding:4px;background-color:#fafafa}div.traceback pre,div.console pre{white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;_white-space:pre}div.pin-prompt{position:absolute;display:none;top:0;bottom:0;left:0;right:0;background:rgba(255,255,255,0.8)}div.pin-prompt .inner{background:#eee;padding:10px 50px;width:350px;margin:10% auto 0 auto;border:1px solid #ccc;border-radius:2px}div.exc-divider{margin:.7em 0 0 -1em;padding:.5em;background:#11557c;color:#ddd;border:1px solid #ddd}.console.active{max-height:0!important;display:none}.hidden{display:none}"
            }
        }

    }
}