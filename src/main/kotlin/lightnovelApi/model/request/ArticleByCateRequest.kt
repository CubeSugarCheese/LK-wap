package lightnovelApi.model.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class ArticleByCateRequest(
    val parent_gid: Int,
    val gid: Int,
    val page: Int,
    val pageSize: Int? = null,
    @Required val security_key: String = ""
)
