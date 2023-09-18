package ru.mishenko.maksim.schedule.data.settings

import ru.mishenko.maksim.schedule.domain.model.Settings

interface SettingsStore {
    fun set(name: String, settings: List<Settings>)
    fun get(name: String): List<Settings>
}