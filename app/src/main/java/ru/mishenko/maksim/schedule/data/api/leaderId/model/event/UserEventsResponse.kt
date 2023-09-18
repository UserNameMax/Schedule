package ru.mishenko.maksim.schedule.data.api.leaderId.model.event

import kotlinx.serialization.Serializable

@Serializable
data class UserEventsResponse(
    val items: List<Item>,
    val meta: Meta
)