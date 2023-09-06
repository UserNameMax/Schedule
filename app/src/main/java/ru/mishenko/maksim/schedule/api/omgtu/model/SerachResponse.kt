package ru.mishenko.maksim.schedule.api.omgtu.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val description: String,
    val id: Int,
    val label: String,
    val type: String
)