package ru.mishenko.maksim.schedule.domain

import ru.mishenko.maksim.schedule.domain.model.Settings

class GetSettingsUseCaseMock : GetSettingsUseCase {
    override fun getSettings(schedule: String): List<Settings> =
        listOf(
            Settings.OmgtuScheduleSettings(
                type = Settings.OmgtuScheduleSettings.Type.Group,
                name = "ИВТ-213"
            )
        )

}