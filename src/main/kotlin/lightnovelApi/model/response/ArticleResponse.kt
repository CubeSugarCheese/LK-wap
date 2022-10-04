package lightnovelApi.model.response

import kotlinx.serialization.Serializable
import lightnovelApi.model.common.*

@Serializable
data class ArticleResponse(
    val aid: Int,
    val already_coin: Int = 0,
    val already_fav: Int = 0,
    val already_follow: Int = 0,
    val already_like: Int = 0,
    val author: Author,
    val balance: List<Balance> = listOf(),
    val banner: String,
    val cache_ver: Int,
    val coins: Int,
    val comments: Int,
    val content: String = "",
    val cover: String,
    val favorites: Int,
    val gid: Int,
    val has_poll: Int,
    val hits: Int,
    val last_time: String,
    val likes: Int,
    val lt: String,
    val only_app: Int = 0,
    val empty: Int = 0,
    val is_top: Int = 0,
    val only_passer: Int,
    val other_recoms: List<Unit>, // Unknown
    val parent_gid: Int,
    val pay_info: PayInfo? = null,
    val res: Res? = null,
    val shares: Int,
    val sid: Int = 0,
    val summary: String,
    val time: String,
    val title: String,
    val uid: Int? = null,
    val poll: Poll? = null,
    val files: List<File>? = null
)





