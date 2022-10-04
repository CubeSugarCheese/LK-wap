package lightnovelApi

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class SeriesAPITest {

    @Test
    fun getInfo() {
        val data = runBlocking { SeriesAPI.getInfo(2191) }
        assert(data.name == "三角的距離無限趨近零（台）")
    }
}