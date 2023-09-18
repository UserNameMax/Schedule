package ru.mishenko.maksim.schedule.data.api.leaderId

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import ru.mishenko.maksim.schedule.data.api.leaderId.model.LeaderIdAuthRequest
import ru.mishenko.maksim.schedule.data.api.leaderId.model.LeaderIdAuthResponse
import ru.mishenko.maksim.schedule.data.api.leaderId.model.LeaderIdUrls
import ru.mishenko.maksim.schedule.data.api.leaderId.model.event.UserEventsResponse

class LeaderIdApiImpl : LeaderIdApi {
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
    private val baseUrl = LeaderIdUrls.apiBaseUrl

    override suspend fun auth(code: String): LeaderIdAuthResponse =
        client.post("$baseUrl/oauth/token") {
            val body = LeaderIdAuthRequest.getAuthByCodeRequest(code)
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()

    override suspend fun userEvents(userId: String, accessToken: String): UserEventsResponse =
        client.get("$baseUrl/users/${userId}/event-participations") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $accessToken")
            }
        }.body()
}