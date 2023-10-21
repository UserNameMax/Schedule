package ru.mishenko.maksim.schedule.data.api.omgtu

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import io.ktor.serialization.kotlinx.json.json
import ru.mishenko.maksim.schedule.BuildConfig
import ru.mishenko.maksim.schedule.data.api.omgtu.model.ScheduleResponse
import ru.mishenko.maksim.schedule.data.api.omgtu.model.SearchResponse
import java.text.SimpleDateFormat
import java.util.Calendar

class OmgtuScheduleApiImpl : OmgtuScheduleApi {
    private val baseUrl = BuildConfig.omgtuScheduleUrl
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("ktorClient", message)
                }
            }
            level = LogLevel.HEADERS
        }
    }

    override suspend fun search(term: String, type: String): List<SearchResponse> =
        client.get("$baseUrl/search") {
            parameter("term", term)
            parameter("type", type)
        }.body()

    override suspend fun schedule(
        id: String,
        type: String,
        start: Calendar,
        finish: Calendar
    ): List<ScheduleResponse> =
        client.get("$baseUrl/schedule") {
            val formatter = SimpleDateFormat("yyyy.MM.dd")
            url {
                appendPathSegments(type, id)
            }
            parameter("start", formatter.format(start.time))
            parameter("finish", formatter.format(finish.time))
        }.body()
}