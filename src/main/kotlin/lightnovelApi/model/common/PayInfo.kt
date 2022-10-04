package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class PayInfo(
    val is_paid: Int,
    val price: Int,
    val price_type: Int,
    val uid: Int
)
