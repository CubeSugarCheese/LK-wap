package lightnovelApi

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class ArticleAPITest {

    @Test
    fun getDetail() {
        val data = runBlocking { ArticleAPI.getDetail(1099200) }
        assert(data.title == "第23话")
    }

}