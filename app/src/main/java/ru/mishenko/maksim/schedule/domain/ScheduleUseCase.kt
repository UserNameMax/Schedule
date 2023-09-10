package ru.mishenko.maksim.schedule.domain

import ru.mishenko.maksim.schedule.domain.GetEventsUseCase.getEvents
import ru.mishenko.maksim.schedule.domain.model.Event

class ScheduleUseCase {
    suspend operator fun invoke(schedule: String = "default"): List<Event> =
        GetSettingsUseCaseMock()
            .getSettings(schedule = schedule)
            .map { it.getEvents() }
            .flatten()
            .sortedBy { it.start }
}