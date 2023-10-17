package ru.mishenko.maksim.schedule.data

import com.google.api.services.calendar.Calendar
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.mishenko.maksim.schedule.data.api.google.GoogleCalendarServiceFactoryImpl
import ru.mishenko.maksim.schedule.data.api.leaderId.LeaderIdApi
import ru.mishenko.maksim.schedule.data.api.leaderId.LeaderIdApiImpl
import ru.mishenko.maksim.schedule.data.api.omgtu.OmgtuScheduleApi
import ru.mishenko.maksim.schedule.data.api.omgtu.OmgtuScheduleApiImpl

val dataDiModule = module {
    single<Calendar> { GoogleCalendarServiceFactoryImpl(androidContext()).getCalendar() }
    single<OmgtuScheduleApi> { OmgtuScheduleApiImpl() }
    single<LeaderIdApi> { LeaderIdApiImpl() }
}