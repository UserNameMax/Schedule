package ru.mishenko.maksim.schedule.data.api.leaderId.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val completed: Boolean,
    val completedAt: String,
    val createdAt: String,
    val event: Event,
    val id: String,
    val moderation: String
)