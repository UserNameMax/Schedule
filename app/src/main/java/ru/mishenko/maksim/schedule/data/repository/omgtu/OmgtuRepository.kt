package ru.mishenko.maksim.schedule.data.repository.omgtu

import ru.mishenko.maksim.schedule.domain.model.Event
import java.util.Calendar

interface OmgtuRepository {
    suspend fun events(group: String, start: Calendar, finish: Calendar): List<Event>
}