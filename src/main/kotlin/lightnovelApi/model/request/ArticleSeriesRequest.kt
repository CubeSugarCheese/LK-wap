package lightnovelApi.model.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class ArticleSeriesRequest(val sid: Long, @Required val security_key: String = "")
