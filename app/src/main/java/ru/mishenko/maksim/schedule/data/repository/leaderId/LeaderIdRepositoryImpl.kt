package ru.mishenko.maksim.schedule.data.repository.leaderId

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.api.leaderId.LeaderIdApi
import ru.mishenko.maksim.schedule.data.api.leaderId.model.event.Item
import ru.mishenko.maksim.schedule.domain.model.Event
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar

class LeaderIdRepositoryImpl : LeaderIdRepository, KoinComponent {
    val api: LeaderIdApi by inject()
    override suspend fun getEvents(userId: String, accessToken: String): List<Event> =
        api.userEvents(userId, accessToken).items.map { it.toEvent() }

    private fun Item.toEvent() = Event(
        name = event.name,
        start = getCalendar(event.dateStart),
        finish = getCalendar(event.dateEnd),
        organizer = "",
        type = event.type.name,
        place = "",
        participant = ""
    )

    private fun getCalendar(date: String): Calendar =
        with(SimpleDateFormat("yyyy-MM-dd HH:mm:SS")) {
            val time = this.parse(date)
            GregorianCalendar().apply { if (time != null) this.time = time }
        }

}
