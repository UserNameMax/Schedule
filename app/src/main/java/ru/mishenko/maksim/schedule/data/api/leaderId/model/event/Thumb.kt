package ru.mishenko.maksim.schedule.data.api.leaderId.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Thumb(
    val `180`: String,
    val `360`: String,
    val `520`: String
)