package ru.mishenko.maksim.schedule.data.api.leaderId.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val full: String,
    val thumb: Thumb
)