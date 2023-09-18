package ru.mishenko.maksim.schedule.data.settings.impl

import ru.mishenko.maksim.schedule.data.settings.SettingsStore
import ru.mishenko.maksim.schedule.domain.model.Settings


class StoreMock : SettingsStore {
    private var store = listOf<Settings>(
        Settings.OmgtuScheduleSettings(
            type = Settings.OmgtuScheduleSettings.Type.Group,
            name = "ИВТ-213"
        )
    )

    override fun set(name: String, settings: List<Settings>) {
        store = settings
    }

    override fun get(name: String): List<Settings> = store
}