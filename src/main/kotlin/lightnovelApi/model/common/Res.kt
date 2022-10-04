package lightnovelApi.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Res(val ids: List<String>, val res_info: Map<String, ResInfo>)
