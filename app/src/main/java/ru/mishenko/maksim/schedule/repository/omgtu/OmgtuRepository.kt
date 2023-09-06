package ru.mishenko.maksim.schedule.repository.omgtu

import ru.mishenko.maksim.schedule.model.Event

interface OmgtuRepository {
    suspend fun events(group: String): List<Event>
}