package ru.mishenko.maksim.schedule.data.api.omgtu

import ru.mishenko.maksim.schedule.data.api.omgtu.model.ScheduleResponse
import ru.mishenko.maksim.schedule.data.api.omgtu.model.SearchResponse
import java.util.Calendar

interface OmgtuScheduleApi {
    suspend fun search(term: String, type: String): List<SearchResponse>
    suspend fun schedule(id: String, type: String, start: Calendar, finish: Calendar): List<ScheduleResponse>
}