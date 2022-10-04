package lightnovelApi.model.response

import kotlinx.serialization.Serializable
import lightnovelApi.model.common.Article
import lightnovelApi.model.common.PageInfo

@Serializable
data class ArticleByCateResponse(
    val list: List<Article>,
    val page_info: PageInfo
)
