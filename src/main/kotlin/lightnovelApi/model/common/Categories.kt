package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val gid: Int,
    val name: String,
    val pic: String? = null,
    val last_time: String
)
