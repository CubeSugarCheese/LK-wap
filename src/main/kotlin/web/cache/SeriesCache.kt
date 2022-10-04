package web.cache

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lightnovelApi.SeriesAPI
import lightnovelApi.model.response.SeriesInfoResponse
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.support.sqlite.insertOrUpdate
import org.slf4j.LoggerFactory
import web.Config
import web.cache.table.SeriesCaches
import web.currentTimeSeconds
import java.sql.DriverManager

object SeriesCache : Cache<SeriesInfoResponse> {
    private val db = Database.connect("jdbc:sqlite:file:./seriesCache.db")

    init {
        val connection = DriverManager.getConnection("jdbc:sqlite:file:./seriesCache.db")
        val statement = connection.createStatement()
        val initSql = """
            |create table if not exists series(
            |id integer not null primary key autoincrement,
            |sid int not null unique,
            |json text,
            |lastUpdateTimestamp bigint not null);""".trimMargin()
        statement.execute(initSql)
        connection.close()
    }

    private fun getSeriesRaw(sid: Int): String? {
        db.from(SeriesCaches)
            .select()
            .where { SeriesCaches.sid eq sid }
            .forEach { row ->
                return row[SeriesCaches.json]
            }
        return null
    }

    override suspend fun get(id: Int): SeriesInfoResponse? {
        return getSeriesRaw(id)?.let { Json.decodeFromString(it) }
    }

    override suspend fun getAndUpdate(id: Int): SeriesInfoResponse {
        val seriesLastUpdateTime = getLastUpdateTimestamp(id)
        val seriesInfo: SeriesInfoResponse
        if (seriesLastUpdateTime == null) {
            seriesInfo = SeriesAPI.getInfo(id)
            insertOrUpdate(seriesInfo)
        } else if (currentTimeSeconds() - seriesLastUpdateTime >= Config.loadFormFileOrDefault().updateTimeSeconds) {
            seriesInfo = get(id)!!
            withContext(Dispatchers.IO) {
                try {
                    insertOrUpdate(SeriesAPI.getInfo(id))
                } catch (e: Exception) {
                    LoggerFactory.getLogger("SeriesCache").error("Error happen while update series cache:", e)
                }
            }
        } else {
            seriesInfo = get(id)!!
        }
        return seriesInfo
    }

    override suspend fun getLastUpdateTimestamp(id: Int): Long? {
        db.from(SeriesCaches)
            .select()
            .where { SeriesCaches.sid eq id }
            .forEach { row ->
                return row[SeriesCaches.lastUpdateTimestamp]
            }
        return null
    }

    override suspend fun insertOrUpdate(data: SeriesInfoResponse) {
        db.insertOrUpdate(SeriesCaches) {
            set(SeriesCaches.sid, data.sid)
            set(SeriesCaches.json, Json.encodeToString(data))
            set(SeriesCaches.lastUpdateTimestamp, currentTimeSeconds())
            onConflict(SeriesCaches.sid) {
                set(SeriesCaches.json, Json.encodeToString(data))
                set(SeriesCaches.lastUpdateTimestamp, currentTimeSeconds())
            }
        }
    }
}