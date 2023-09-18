package ru.mishenko.maksim.schedule.data.api.leaderId.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Timezone(
    val minutes: Int,
    val value: String
)