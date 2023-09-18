package ru.mishenko.maksim.schedule.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.repository.leaderId.LeaderIdRepository
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepository
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.domain.model.Settings

object GetEventsUseCase : KoinComponent {
    private val omgtuRepository: OmgtuRepository by inject()
    private val leaderIdRepository: LeaderIdRepository by inject()
    suspend fun Settings.getEvents(): List<Event> =
        when (this) {
            is Settings.OmgtuScheduleSettings -> omgtuRepository.events(group = this.name)
            is Settings.LeaderIdSettings -> leaderIdRepository.getEvents(
                userId = this.userId,
                accessToken = this.accessToken
            )
        }
}