package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Option(
    val oid: Int,
    val percent: Int,
    val text: String,
    val votes: Int
)