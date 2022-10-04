package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Medal(
    val desc: String,
    val equip: Int = 0,
    val expiration: String? = null,
    val img: String,
    val medal_id: Int,
    val name: String,
    val type: Int
)