package web.cache

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import lightnovelApi.ArticleAPI
import lightnovelApi.model.response.ArticleResponse
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.support.sqlite.insertOrUpdate
import org.slf4j.LoggerFactory
import web.cache.table.ArticleCaches
import web.Config
import web.currentTimeSeconds
import java.sql.DriverManager

object ArticleCache : Cache<ArticleResponse> {
    private val db = Database.connect("jdbc:sqlite:file:./articleCache.db")

    init {
        val connection = DriverManager.getConnection("jdbc:sqlite:file:./articleCache.db")
        val statement = connection.createStatement()
        val initSql = """
            |create table if not exists articles(
            |id integer not null primary key autoincrement,
            |aid int not null unique,
            |json text,
            |lastUpdateTimestamp bigint not null);""".trimMargin()
        statement.execute(initSql)
        connection.close()
    }

    private fun getArticleRaw(aid: Int): String? {
        db.from(ArticleCaches)
            .select()
            .where { ArticleCaches.aid eq aid }
            .forEach { row ->
                return row[ArticleCaches.json]
            }
        return null
    }

    override suspend fun get(id: Int): ArticleResponse? {
        return getArticleRaw(id)?.let { Json.decodeFromString(it) }
    }

    override suspend fun getLastUpdateTimestamp(id: Int): Long? {
        db.from(ArticleCaches)
            .select()
            .where { ArticleCaches.aid eq id }
            .forEach { row ->
                return row[ArticleCaches.lastUpdateTimestamp]
            }
        return null
    }

    override suspend fun insertOrUpdate(data: ArticleResponse) {
        db.insertOrUpdate(ArticleCaches) {
            set(ArticleCaches.aid, data.aid)
            set(ArticleCaches.json, Json.encodeToString(data))
            set(ArticleCaches.lastUpdateTimestamp, currentTimeSeconds())
            onConflict(ArticleCaches.aid) {
                set(ArticleCaches.json, Json.encodeToString(data))
                set(ArticleCaches.lastUpdateTimestamp, currentTimeSeconds())
            }
        }
    }

    override suspend fun getAndUpdate(id: Int): ArticleResponse {
        val lastUpdateTimestamp = getLastUpdateTimestamp(id)
        val article: ArticleResponse
        if (lastUpdateTimestamp == null) {
            article = ArticleAPI.getDetail(id, Config.loadFormFileOrDefault().security_key)
            insertOrUpdate(article)
        } else if (currentTimeSeconds() - lastUpdateTimestamp >= Config.loadFormFileOrDefault().updateTimeSeconds) {
            article = get(id)!!
            withContext(Dispatchers.IO) {
                try {
                    insertOrUpdate(ArticleAPI.getDetail(id, Config.loadFormFileOrDefault().security_key))
                } catch (e: Exception) {
                    LoggerFactory.getLogger("ArticleCache").error("Error happen while update article cache:", e)
                }
            }
        } else {
            article = get(id)!!
        }
        return article
    }

    fun getAllArticle(): List<ArticleResponse?> {
        return db.from(ArticleCaches)
            .select()
            .map { row -> row[ArticleCaches.json]?.let { Json.decodeFromString<ArticleResponse>(it) } }
    }
}