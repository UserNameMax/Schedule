package ru.mishenko.maksim.schedule.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.repository.google.GoogleCalendarRepository
import ru.mishenko.maksim.schedule.data.repository.leaderId.LeaderIdRepository
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepository
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.domain.model.Settings
import java.util.Calendar

object GetEventsUseCase : KoinComponent {
    private val omgtuRepository: OmgtuRepository by inject()
    private val leaderIdRepository: LeaderIdRepository by inject()
    private val googleCalendarRepository: GoogleCalendarRepository by inject()
    suspend fun Settings.getEvents(start: Calendar, finish: Calendar): List<Event> =
        when (this) {
            is Settings.OmgtuScheduleSettings -> omgtuRepository.events(
                group = name,
                start = start,
                finish = finish
            ).run {
                if (!showMilitaryDepartment)
                    filter { it.place != "ВК" }
                else this
            }.run {
                if (subGroup != null)
                    filter { !it.participant.contains("/") || it.participant.contains("/${subGroup}") }
                else this
            }.run {
                if (!showDebts)
                    filter { it.type != "Пересдача задолженностей" }
                else this
            }

            is Settings.LeaderIdSettings -> leaderIdRepository.getEvents(
                userId = userId,
                accessToken = accessToken
            )

            is Settings.GoogleCalendarSettings -> googleCalendarRepository.getEvent(
                startDate = start,
                finishDate = finish,
                calendarId = calendarId
            )
        }
}