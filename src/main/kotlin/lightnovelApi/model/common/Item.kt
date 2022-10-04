package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val gid: Int,
    val name: String,
    val logo: String,
    val cover_type: Int,
    val order: Int
)
