package ru.mishenko.maksim.schedule.data.api.leaderId.model

import kotlinx.serialization.Serializable

@Serializable
data class LeaderIdAuthResponse(
    val access_token: String,
    val refresh_token: String,
    val user_id: Int,
    val user_validated: Boolean
)