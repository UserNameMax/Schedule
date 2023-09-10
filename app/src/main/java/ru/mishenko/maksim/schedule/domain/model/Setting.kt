package ru.mishenko.maksim.schedule.domain.model

sealed interface Settings {
    data class OmgtuScheduleSettings(val type: Type, val name: String) : Settings {
        enum class Type {
            Auditorium, Group, Person
        }
    }
}
