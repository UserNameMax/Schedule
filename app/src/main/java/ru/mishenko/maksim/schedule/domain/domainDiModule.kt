package ru.mishenko.maksim.schedule.domain

import org.koin.dsl.module
import ru.mishenko.maksim.schedule.data.repository.google.GoogleCalendarRepository
import ru.mishenko.maksim.schedule.data.repository.google.GoogleCalendarRepositoryImpl
import ru.mishenko.maksim.schedule.data.repository.leaderId.LeaderIdRepository
import ru.mishenko.maksim.schedule.data.repository.leaderId.LeaderIdRepositoryImpl
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepository
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepositoryImpl
import ru.mishenko.maksim.schedule.data.settings.SettingsStore
import ru.mishenko.maksim.schedule.data.settings.impl.StoreMock
import ru.mishenko.maksim.schedule.domain.impl.GetSettingsUseCaseImpl

val domainDiModule = module {
    single<OmgtuRepository> { OmgtuRepositoryImpl() }
    single<GetSettingsUseCase> { GetSettingsUseCaseImpl() }
    single<SettingsStore> { StoreMock() }
    single<LeaderIdRepository> { LeaderIdRepositoryImpl() }
    single<AddSettingsUseCase> { AddSettingsUseCase() }
    single<GoogleCalendarRepository> { GoogleCalendarRepositoryImpl() }
}