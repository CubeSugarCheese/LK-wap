package web

import com.github.houbb.opencc4j.util.ZhConverterUtil
import io.ktor.server.application.*
import io.ktor.util.*
import io.ktor.util.logging.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption

/**
 * 将繁体转换为简体
 *
 * @return 简体字符串
 * */
fun String.toSimple(): String = ZhConverterUtil.toSimple(this)

val ApplicationCall.logger: Logger
    get() = this.application.environment.log

inline fun <T> List<T>.findIndex(predicate: (T) -> Boolean): Int? {
    for (i in this.withIndex()) {
        if (predicate(i.value)) {
            return i.index
        }
    }
    return null
}

fun pager(text: String, target: Int = Config.loadFormFileOrDefault().articlePageSize): List<String> {
    val doc = Jsoup.parse(text)
    val result: MutableList<String> = mutableListOf()
    var count = 0
    var cache = ""
    for (i in doc.body().childNodes()) {
        if (count < target) {
            if (i is Element) {
                cache += i.outerHtml()
                count += i.text().length
            } else {
                cache += i.outerHtml()
                count += i.toString().length
            }
        } else {
            result.add(cache)
            cache = ""
            count = 0
        }
    }
    result.add(cache)
    return result
}

/**
 * remove the file content
 * */
fun File.removeContent() {
    Files.newBufferedWriter(Path.of(this.path), StandardOpenOption.TRUNCATE_EXISTING)
}

fun currentTimeSeconds() = System.currentTimeMillis() / 1000 // 毫秒转为秒

fun StringValues.format(): String {
    var result = ""
    forEach { s, strings -> result += "${s}=${strings[0]};\n" }
    return result
}

