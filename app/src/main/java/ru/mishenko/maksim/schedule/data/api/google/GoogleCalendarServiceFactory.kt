package ru.mishenko.maksim.schedule.data.api.google

import com.google.api.services.calendar.Calendar

interface GoogleCalendarServiceFactory {
    fun getCalendar(): Calendar
}