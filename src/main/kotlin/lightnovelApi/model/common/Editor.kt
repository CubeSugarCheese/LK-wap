package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Editor(
    val articles: Int,
    val avatar: String,
    val ban_end_date: String,
    val banner: String,
    val comments: Int,
    val favorites: Int,
    val followers: Int,
    val following: Int,
    val gender: Int,
    val level: Level,
    val medals: List<Medal>,
    val nickname: String,
    val passer: Int,
    val sign: String,
    val status: Int,
    val uid: Int
)