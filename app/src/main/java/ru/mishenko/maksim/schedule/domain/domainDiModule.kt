package ru.mishenko.maksim.schedule.domain

import org.koin.dsl.module
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepository
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepositoryImpl

val domainDiModule = module {
    single<OmgtuRepository> { OmgtuRepositoryImpl() }
    single<GetSettingsUseCase> { GetSettingsUseCaseMock() }
}