package lightnovelApi

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CategoryAPITest {

    @Test
    fun getArticleCates() {
        val data = runBlocking { CategoryAPI.getArticleCates(2, true) }
        assert(data[0].name != "")
    }

    @Test
    fun getCategories() {
        val data = runBlocking { CategoryAPI.getCategories(37) }
        assert(data[0].name == "全部")
    }

    @Test
    fun getArticleByCate() {
        val data = runBlocking { CategoryAPI.getArticleByCate(122,1,37) }
        assert(data.page_info.has_next != 2)
    }
}