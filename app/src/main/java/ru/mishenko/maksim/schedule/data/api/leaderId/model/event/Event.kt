package ru.mishenko.maksim.schedule.data.api.leaderId.model.event

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val access: String,
    val createdBy: Int? = null,
    val cycleId: Int?,
    val dateEnd: String,
    val dateStart: String,
    val format: String,
    val hashTags: List<String>,
    val id: Int,
    val inCalendar: Boolean,
    val inDigest: Boolean?,
    val info: String,
    val isCompetition: Boolean,
    val live: List<String>,
    val livePublic: Boolean,
    val name: String,
    val participationFormat: String,
    val photo: Photo,
    val promoted: Boolean,
    val registrationDateEnd: String,
    val registrationDateStart: String,
    val teamSizeMax: Int?,
    val teamSizeMin: Int?,
    val teamType: String?,
    val timezone: Timezone,
    val type: Type
)