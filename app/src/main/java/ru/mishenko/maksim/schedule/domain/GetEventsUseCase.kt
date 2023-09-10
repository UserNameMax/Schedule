package ru.mishenko.maksim.schedule.domain

import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepositoryImpl
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.domain.model.Settings

object GetEventsUseCase {
    suspend fun Settings.getEvents(): List<Event> =
        when (this) {
            is Settings.OmgtuScheduleSettings -> OmgtuRepositoryImpl().events(this.name)
        }
}