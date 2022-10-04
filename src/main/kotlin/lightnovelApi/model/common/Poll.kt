package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Poll(
    val expiration: String,
    val max_choices: Int,
    val multiple: Int,
    val options: List<Option>,
    val title: String,
    val voters: Int
)