package web.cache

interface Cache<T> {
    suspend fun get(id: Int): T?
    suspend fun insertOrUpdate(data: T)
    suspend fun getAndUpdate(id: Int): T
    suspend fun getLastUpdateTimestamp(id: Int): Long?
}