package ru.mishenko.maksim.schedule.data.repository.leaderId

import ru.mishenko.maksim.schedule.domain.model.Event


interface LeaderIdRepository {
    suspend fun getEvents(userId: String, accessToken: String): List<Event>
}