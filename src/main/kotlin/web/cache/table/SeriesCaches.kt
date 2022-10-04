package web.cache.table

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.long
import org.ktorm.schema.text

object SeriesCaches : Table<Nothing>("series") {
    @Suppress("unused")
    val id = int("id").primaryKey()
    val sid = int("sid")
    val lastUpdateTimestamp = long("lastUpdateTimestamp")
    val json = text("json")
}

