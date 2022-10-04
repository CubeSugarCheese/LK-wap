package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Level(
    val exp: Int,
    val level: Int,
    val name: String,
    val next_exp: Int
)