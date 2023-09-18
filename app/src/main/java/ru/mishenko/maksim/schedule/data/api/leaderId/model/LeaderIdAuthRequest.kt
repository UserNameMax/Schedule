package ru.mishenko.maksim.schedule.data.api.leaderId.model

import kotlinx.serialization.Serializable
import ru.mishenko.maksim.schedule.BuildConfig

@Serializable
data class LeaderIdAuthRequest(
    val client_id: String,
    val client_secret: String,
    val grant_type: String,
    val code: String
){
    companion object{
        fun getAuthByCodeRequest(code:String) = LeaderIdAuthRequest(
            client_id = BuildConfig.leaderApiKey,
            client_secret = BuildConfig.leaderIdSecret,
            grant_type = "authorization_code",
            code = code
        )
    }
}
