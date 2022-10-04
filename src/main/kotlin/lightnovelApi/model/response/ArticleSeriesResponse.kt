package lightnovelApi.model.response

import kotlinx.serialization.Serializable
import lightnovelApi.model.common.Article
import lightnovelApi.model.common.Editor

@Serializable
data class ArticleSeriesResponse(
    val articles: List<Article>,
    val author: String,
    val banner: String,
    val characters: List<Unit>, // Unknown
    val cover: String,
    val cover_type: Int,
    val editors: List<Editor>,
    val gid: Int,
    val hits: Int,
    val intro: String,
    val last_time: String,
    val likes: Int,
    val name: String,
    val parent_gid: Int,
    val rate: Int,
    val rates: Int,
    val score: Int,
    val sid: Int
)

