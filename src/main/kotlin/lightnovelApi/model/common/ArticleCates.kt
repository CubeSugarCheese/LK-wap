package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class ArticleCates(
    val gid: Int,
    val name: String,
    val logo: String,
    val cover_type: Int,
    val order: Int,
    val items: List<Item>
)
