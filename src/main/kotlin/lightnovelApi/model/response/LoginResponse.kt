package lightnovelApi.model.response

import lightnovelApi.model.common.Balance
import lightnovelApi.model.common.Level
import lightnovelApi.model.common.Medal
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val articles: Int,
    val avatar: String,
    val balance: Balance,
    val ban_end_date: String,
    val banner: String,
    val comments: Int,
    val favorites: Int,
    val followers: Int,
    val following: Int,
    val gender: Int,
    val level: Level,
    val medals: List<Medal>,
    val nickname: String,
    val passer: Int,
    val security_key: String,
    val sign: String,
    val status: Int,
    val uid: Int
)



