package ru.mishenko.maksim.schedule.data.repository.google

import ru.mishenko.maksim.schedule.domain.model.Event
import java.util.Calendar

interface GoogleCalendarRepository {
    fun getEvent(startDate: Calendar, finishDate: Calendar, calendarId: String): List<Event>
}