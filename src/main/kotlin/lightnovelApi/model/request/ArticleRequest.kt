package lightnovelApi.model.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import lightnovelApi.model.common.Simple

@Serializable
data class ArticleRequest(
    val aid: Int,
    @Required val simple: Simple = Simple.Disable,
    val security_key: String = ""
)
