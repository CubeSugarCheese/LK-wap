package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class ResInfo(
    val ext: String,
    val filename: String,
    val height: Int,
    val resid: Int,
    val url: String,
    val width: Int
)