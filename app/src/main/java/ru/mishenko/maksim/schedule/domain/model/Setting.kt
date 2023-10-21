package ru.mishenko.maksim.schedule.domain.model

sealed interface Settings {
    data class OmgtuScheduleSettings(
        val type: Type,
        val name: String,
        val subGroup: String? = null,
        val showMilitaryDepartment: Boolean = true,
        val showDebts: Boolean = true
    ) : Settings {
        enum class Type {
            Auditorium, Group, Person
        }
    }

    data class LeaderIdSettings(
        val userId: String,
        val refreshToken: String,
        val accessToken: String
    ) : Settings

    data class GoogleCalendarSettings(val calendarId: String) : Settings
}
