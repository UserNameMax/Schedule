package ru.mishenko.maksim.schedule.domain.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.data.settings.SettingsStore
import ru.mishenko.maksim.schedule.domain.GetSettingsUseCase
import ru.mishenko.maksim.schedule.domain.model.Settings

class GetSettingsUseCaseImpl : GetSettingsUseCase, KoinComponent {

    private val store: SettingsStore by inject()
    override fun getSettings(schedule: String): List<Settings> = store.get(schedule)
}