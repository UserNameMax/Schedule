package ru.mishenko.maksim.schedule.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.domain.GetEventsUseCase.getEvents
import ru.mishenko.maksim.schedule.domain.model.Event
import java.util.Calendar

class ScheduleUseCase : KoinComponent {
    private val getSettingsUseCase: GetSettingsUseCase by inject()
    suspend operator fun invoke(
        schedule: String = "default",
        start: Calendar,
        finish: Calendar
    ): List<Event> =
        getSettingsUseCase
            .getSettings(schedule = schedule)
            .map { it.getEvents(start = start, finish = finish) }
            .flatten()
            .sortedBy { it.start }
            .filter { it.start >= start && it.finish <= finish }

}