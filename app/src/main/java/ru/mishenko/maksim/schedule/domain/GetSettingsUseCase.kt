package ru.mishenko.maksim.schedule.domain

import ru.mishenko.maksim.schedule.domain.model.Settings

interface GetSettingsUseCase {
    fun getSettings(schedule: String): List<Settings>
}