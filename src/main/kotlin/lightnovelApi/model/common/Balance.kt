package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Balance(
    val balance: Int,
    val coin: Int
)
