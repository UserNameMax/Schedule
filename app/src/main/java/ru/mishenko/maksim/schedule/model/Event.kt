package ru.mishenko.maksim.schedule.model

import java.util.Calendar

data class Event(
    val name: String,
    val start: Calendar,
    val finish: Calendar,
    val organizer: String,
    val type: String,
    val place: String,
    val participant: String
)
