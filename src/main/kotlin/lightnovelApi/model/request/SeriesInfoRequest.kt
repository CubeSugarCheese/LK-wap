package lightnovelApi.model.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class SeriesInfoRequest(
    val sid: Int,
    @Required val security_key: String = ""
)
