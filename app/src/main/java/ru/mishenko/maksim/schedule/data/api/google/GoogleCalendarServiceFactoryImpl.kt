package ru.mishenko.maksim.schedule.data.api.google

import android.content.Context
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.GoogleCredentials
import ru.mishenko.maksim.schedule.R

class GoogleCalendarServiceFactoryImpl(private val context: Context):GoogleCalendarServiceFactory {
    override fun getCalendar(): Calendar {
        val jsonFactory = GsonFactory.getDefaultInstance()!!
        val httpTransport = GoogleNetHttpTransport.newTrustedTransport()!!
        val inputStream = context.resources.openRawResource(R.raw.schedule_402316_3e2397ebb45a)
        val googleCredentials = GoogleCredentials.fromStream(inputStream).createScoped(CalendarScopes.CALENDAR)
        return Calendar.Builder(httpTransport, jsonFactory, HttpCredentialsAdapter(googleCredentials))
            .setApplicationName("APPLICATION_NAME")
            .build()
    }
}