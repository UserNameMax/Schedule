package ru.mishenko.maksim.schedule.repository.omgtu

import ru.mishenko.maksim.schedule.api.omgtu.OmgtuScheduleApi
import ru.mishenko.maksim.schedule.api.omgtu.OmgtuScheduleApiImpl
import ru.mishenko.maksim.schedule.api.omgtu.model.ScheduleResponse
import ru.mishenko.maksim.schedule.model.Event
import java.text.SimpleDateFormat
import java.util.GregorianCalendar

class OmgtuRepositoryImpl(
    private val api: OmgtuScheduleApi = OmgtuScheduleApiImpl()
) : OmgtuRepository {
    override suspend fun events(group: String): List<Event> =
        with(api.search(group, "group").firstOrNull()) {
            if (this == null) listOf()
            else api.schedule(id.toString(), "group").map { it.toEvent() }.sortedBy { it.start }
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

