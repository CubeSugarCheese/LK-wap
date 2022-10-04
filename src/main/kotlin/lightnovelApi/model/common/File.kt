package lightnovelApi.model.common

import kotlinx.serialization.Serializable


@Serializable
data class File(
    val desc: String,
    val ext: String,
    val name: String,
    val size: Int,
    val url: String
)