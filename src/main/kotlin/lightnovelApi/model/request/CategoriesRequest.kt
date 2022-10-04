package lightnovelApi.model.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesRequest(
    val parent_gid: Int,
    @Required val security_key: String = ""
)
