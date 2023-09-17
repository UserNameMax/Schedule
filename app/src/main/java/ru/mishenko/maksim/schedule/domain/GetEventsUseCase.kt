package ru.mishenko.maksim.schedule.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepository
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.domain.model.Settings

object GetEventsUseCase : KoinComponent {
    private val omgtuRepository: OmgtuRepository by inject()
    suspend fun Settings.getEvents(): List<Event> =
        when (this) {
            is Settings.OmgtuScheduleSettings -> omgtuRepository.events(this.name)
        }
}