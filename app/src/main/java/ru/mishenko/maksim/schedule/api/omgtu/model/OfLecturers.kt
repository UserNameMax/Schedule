package ru.mishenko.maksim.schedule.api.omgtu.model

import kotlinx.serialization.Serializable

@Serializable
data class OfLecturers(
    val lecturer: String,
    val lecturerCustomUID: String?,
    val lecturerEmail: String?,
    val lecturerGUID: String?,
    val lecturerOid: Int,
    val lecturerUID: String?,
    val lecturer_post_oid: Int,
    val lecturer_rank: String,
    val lecturer_title: String
)