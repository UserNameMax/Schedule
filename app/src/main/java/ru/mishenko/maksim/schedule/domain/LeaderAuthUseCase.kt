package ru.mishenko.maksim.schedule.domain

import android.util.Log
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.api.leaderId.LeaderIdApi
import ru.mishenko.maksim.schedule.domain.model.Settings

class LeaderAuthUseCase : KoinComponent {
    private val addSettingsUseCase: AddSettingsUseCase by inject()
    private val api: LeaderIdApi by inject()
    suspend fun auth(schedule: String, code: String) = with(api.auth(code)) {
        Log.e("token", access_token)
        addSettingsUseCase.addSettings(
            schedule, Settings.LeaderIdSettings(
                refreshToken = access_token,
                accessToken = refresh_token,
                userId = user_id.toString()
            )
        )
    }
}