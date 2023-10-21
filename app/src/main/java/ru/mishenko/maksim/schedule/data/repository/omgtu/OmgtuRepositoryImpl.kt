package ru.mishenko.maksim.schedule.data.repository.omgtu

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.api.omgtu.OmgtuScheduleApi
import ru.mishenko.maksim.schedule.data.api.omgtu.model.ScheduleResponse
import ru.mishenko.maksim.schedule.domain.model.Event
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.GregorianCalendar

class OmgtuRepositoryImpl() : OmgtuRepository, KoinComponent {
    private val api: OmgtuScheduleApi by inject()
    override suspend fun events(group: String, start: Calendar, finish: Calendar): List<Event> =
        with(api.search(group, "group").firstOrNull()) {
            if (this == null) listOf()
            else api.schedule(id = id.toString(), type = "group", start = start, finish = finish)
                .map { it.toEvent() }
                .sortedBy { it.start }
        }

    private val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")
    private fun ScheduleResponse.toEvent(): Event =
        Event(
            name = discipline,
            start = GregorianCalendar().apply {
                timeInMillis = simpleDateFormat.parse("$date $beginLesson")?.time ?: 0
            },
            finish = GregorianCalendar().apply {
                timeInMillis = simpleDateFormat.parse("$date $endLesson")?.time ?: 0
            },
            organizer = lecturer,
            type = kindOfWork,
            place = auditorium,
            participant = when (kindOfWork) {
                "Лекция" -> stream ?: ""
                "Лабораторные работы" -> subGroup ?: ""
                "Практические занятия" -> group ?: ""
                else -> ""
            }
        )
}

