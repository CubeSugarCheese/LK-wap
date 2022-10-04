package web

import kotlinx.serialization.Serializable
import kotlinx.serialization.Required
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.apache.logging.log4j.LogManager
import java.io.File

@Serializable
data class Config(
    @Required val host: String = "0.0.0.0",
    @Required val port: Int = 10023,
    @Required val pageSize: Int = 40,
    @Required val articlePageSize: Int = 1000,
    @Required val security_key: String = "",
    @Required val updateTimeSeconds: Int = 600,
) {
    companion object {
        private var config: Config? = null

        fun loadFormFileOrDefault(): Config {
            val configFile = File("config.json")
            if (!configFile.exists()) configFile.createNewFile()
            val configJson = configFile.readText()
            return if (config == null) {
                val configFromFile: Config = try {
                    LogManager.getLogger("Config").info("Read config from ${configFile.absolutePath}")
                    Json.decodeFromString(configJson)
                } catch (e: SerializationException) {
                    configFile.removeContent()
                    configFile.writeText(Json.encodeToString(Config()))
                    LogManager.getLogger("Config").info("Generated default config file at ${configFile.absolutePath}")
                    Config()
                }
                config = configFromFile
                configFromFile
            } else {
                config as Config
            }
        }
    }
}
