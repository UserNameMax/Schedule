package ru.mishenko.maksim.schedule.data

import org.koin.dsl.module
import ru.mishenko.maksim.schedule.data.api.leaderId.LeaderIdApi
import ru.mishenko.maksim.schedule.data.api.leaderId.LeaderIdApiImpl
import ru.mishenko.maksim.schedule.data.api.omgtu.OmgtuScheduleApi
import ru.mishenko.maksim.schedule.data.api.omgtu.OmgtuScheduleApiImpl

val dataDiModule = module {
    single<OmgtuScheduleApi> { OmgtuScheduleApiImpl() }
    single<LeaderIdApi> { LeaderIdApiImpl() }
}