package ru.mishenko.maksim.schedule.data.api.leaderId.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val paginationPage: Int,
    val paginationPageCount: Int,
    val paginationSize: Int,
    val totalCount: Int
)