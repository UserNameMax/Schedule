package ru.mishenko.maksim.schedule.data.repository.omgtu

import ru.mishenko.maksim.schedule.domain.model.Event

interface OmgtuRepository {
    suspend fun events(group: String): List<Event>
}