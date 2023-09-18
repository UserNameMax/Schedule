package ru.mishenko.maksim.schedule.data.api.leaderId

import ru.mishenko.maksim.schedule.data.api.leaderId.model.LeaderIdAuthResponse
import ru.mishenko.maksim.schedule.data.api.leaderId.model.event.UserEventsResponse

interface LeaderIdApi {
    suspend fun auth(code: String): LeaderIdAuthResponse
    suspend fun userEvents(userId: String, accessToken: String): UserEventsResponse
}