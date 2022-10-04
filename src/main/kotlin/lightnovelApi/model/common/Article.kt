package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val aid: Int,
    val author: String? = null,
    val avatar: String? = null,
    val banner: String,
    val comments: Int,
    val cover: String,
    val cover_type: Int? = null,
    val gid: Int? = null,
    val group_name: String? = null,
    val hits: Int,
    val is_top: Int = 0,
    val last_time: String,
    val sid: Int = 0,
    val time: String,
    val title: String,
    val uid: Int = 0,
    val empty: Int = 0,
    val order: Int = 0
)