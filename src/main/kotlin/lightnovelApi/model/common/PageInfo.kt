package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class
PageInfo(
    val count: Int,
    val size: Int,
    val cur: Int,
    val prev: Int,
    val next: Int,
    val has_prev: Int,
    val has_next: Int,
    val model: Int,
    val support_model: List<Int>
)