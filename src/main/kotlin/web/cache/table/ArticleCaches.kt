package web.cache.table

import org.ktorm.schema.*

object ArticleCaches : Table<Nothing>("articles") {
    @Suppress("unused")
    val id = int("id").primaryKey()
    val aid = int("aid")
    val lastUpdateTimestamp = long("lastUpdateTimestamp")
    val json = text("json")
}

