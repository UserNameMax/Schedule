package ru.mishenko.maksim.schedule.api.omgtu

import ru.mishenko.maksim.schedule.api.omgtu.model.ScheduleResponse
import ru.mishenko.maksim.schedule.api.omgtu.model.SearchResponse

interface OmgtuScheduleApi {
    suspend fun search(term: String, type: String): List<SearchResponse>
    suspend fun schedule(id: String, type: String): List<ScheduleResponse>
}