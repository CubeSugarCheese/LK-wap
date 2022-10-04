package lightnovelApi.model.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class ArticleCatesRequest(
    val depth: Int,
    val cache: Boolean,
    @Required val security_key: String = ""
)
