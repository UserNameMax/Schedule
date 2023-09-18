package ru.mishenko.maksim.schedule.data.api.leaderId.model

import ru.mishenko.maksim.schedule.BuildConfig

object LeaderIdUrls {
    const val apiBaseUrl = "https://apps.leader-id.ru/api/v1"
    fun getCodeUrl(apiKey: String = BuildConfig.leaderApiKey, redirectUri: String) =
        "https://leader-id.ru/apps/authorize?client_id=${apiKey}&redirect_uri=${redirectUri}&response_type=code"
}