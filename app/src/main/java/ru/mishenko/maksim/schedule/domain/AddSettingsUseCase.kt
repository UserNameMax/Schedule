package ru.mishenko.maksim.schedule.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.settings.SettingsStore
import ru.mishenko.maksim.schedule.domain.model.Settings

class AddSettingsUseCase : KoinComponent {
    private val store: SettingsStore by inject()
    fun addSettings(schedule: String, settings: Settings) =
        with(store.get(schedule)) {
            store.set(schedule, this + settings)
        }
}