package ru.mishenko.maksim.schedule.ui.schedule

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mishenko.maksim.schedule.domain.model.Event

@Composable
fun ScheduleScreen(events: List<Event>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        EventList(events = events)
    }
}