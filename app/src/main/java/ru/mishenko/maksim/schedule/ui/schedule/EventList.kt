package ru.mishenko.maksim.schedule.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mishenko.maksim.schedule.model.Event

@Composable
fun EventList(events: List<Event>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        events.forEach {
            EventCard(modifier = Modifier.fillMaxWidth(), event = it)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}