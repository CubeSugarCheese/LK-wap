package lightnovelApi.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Response<T> (val code: Int = 0, val data: T, val t: Long)