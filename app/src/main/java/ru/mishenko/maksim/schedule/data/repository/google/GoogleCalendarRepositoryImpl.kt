package ru.mishenko.maksim.schedule.data.repository.google

import com.google.api.client.util.DateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.domain.model.Event
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

class GoogleCalendarRepositoryImpl : GoogleCalendarRepository, KoinComponent {
    private val calendar: com.google.api.services.calendar.Calendar by inject()
    override fun getEvent(
        startDate: Calendar,
        finishDate: Calendar,
        calendarId: String
    ): List<Event> = calendar.events().list(calendarId)
        .apply {
            timeMin = DateTime(startDate.timeInMillis)
            timeMax = DateTime(finishDate.timeInMillis)
        }
        .execute().items.mapNotNull { it?.toEvent() }
        .filter { it.start >= startDate && it.finish <= finishDate }

    private fun com.google.api.services.calendar.model.Event.toEvent() =
        Event(
            name = summary ?: "",
            start = GregorianCalendar().also {
                it.time = Date(start?.dateTime?.value ?: start?.date?.value ?: 0)
            },
            finish = GregorianCalendar().also {
                it.time = Date(end?.dateTime?.value ?: end?.date?.value ?: 0)
            },
            organizer = organizer?.displayName ?: "",
            type = "Google Event",
            place = location ?: "",
            participant = "" 
        )
}
