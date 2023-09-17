package ru.mishenko.maksim.schedule.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.domain.GetEventsUseCase.getEvents
import ru.mishenko.maksim.schedule.domain.model.Event

class ScheduleUseCase : KoinComponent {
    private val getSettingsUseCase: GetSettingsUseCase by inject()
    suspend operator fun invoke(schedule: String = "default"): List<Event> =
        getSettingsUseCase
            .getSettings(schedule = schedule)
            .map { it.getEvents() }
            .flatten()
            .sortedBy { it.start }
}