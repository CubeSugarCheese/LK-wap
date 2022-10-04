package lightnovelApi.model.response

import kotlinx.serialization.Serializable
import lightnovelApi.model.common.Article
import lightnovelApi.model.common.OtherUser

@Serializable
data class SeriesInfoResponse(
    val sid: Int,
    val name: String,
    val gid: Int,
    val parent_gid: Int,
    val author: String,
    val intro: String,
    val banner: String,
    val rate: Int,
    val cover: String,
    val cover_type: Int,
    val rates: Int,
    val last_time: String,
    val hits: Int,
    val likes: Int,
    val editors: List<OtherUser>,
    val score: Double,
    val characters: List<Unit>,
    val articles: List<Article>,
)
