package ru.mishenko.maksim.schedule.ui.schedule

import ru.mishenko.maksim.schedule.domain.model.Event
import java.util.Calendar

data class EventListUi(
    val day: Calendar,
    val events: List<Event>
)
