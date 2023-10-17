package ru.mishenko.maksim.schedule.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.repository.google.GoogleCalendarRepository
import ru.mishenko.maksim.schedule.data.repository.leaderId.LeaderIdRepository
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepository
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.domain.model.Settings
import java.util.Calendar
import java.util.GregorianCalendar

object GetEventsUseCase : KoinComponent {
    private val omgtuRepository: OmgtuRepository by inject()
    private val leaderIdRepository: LeaderIdRepository by inject()
    private val googleCalendarRepository: GoogleCalendarRepository by inject()
    suspend fun Settings.getEvents(): List<Event> =
        when (this) {
            is Settings.OmgtuScheduleSettings -> omgtuRepository.events(group = name)
            is Settings.LeaderIdSettings -> leaderIdRepository.getEvents(
                userId = userId,
                accessToken = accessToken
            )

            is Settings.GoogleCalendarSettings -> googleCalendarRepository.getEvent(
                startDate = monday(),
                finishDate = sunday(),
                calendarId = calendarId
            )
        }

    private fun monday() =
        GregorianCalendar().apply { add(Calendar.DAY_OF_WEEK, -get(Calendar.DAY_OF_WEEK)) }

    private fun sunday() = monday().apply { add(Calendar.DAY_OF_WEEK, 6) }
}