package lightnovelApi.model.request

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import lightnovelApi.model.common.Client
import lightnovelApi.model.common.Gzip
import lightnovelApi.model.common.IsEncrypted
import lightnovelApi.model.common.Platform

@Serializable
data class Request<T>(
    @Required val client: Client = Client.web,
    val d: T,
    val gz: Gzip = Gzip.Disable,
    val is_encrypted: IsEncrypted = IsEncrypted.Disable,
    @Required val platform: Platform = Platform.android,
    val sign: String? = null,
    val ver_code: Int = 190,
    @Required val ver_name: String = "0.11.50"
)

