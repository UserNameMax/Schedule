package ru.mishenko.maksim.schedule.ui.schedule

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat

@Composable
fun WeekEventsList(events: List<EventListUi>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(20.dp))
        events.forEach {
            DayEventsList(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary),
                events = it
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun DayEventsList(modifier: Modifier = Modifier, events: EventListUi) {
    val dayNameFormater = SimpleDateFormat("EEEE")
    val dateFormater = SimpleDateFormat("dd.MM.yyyy")
    Column(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(). padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = dateFormater.format(events.day.time))
            Text(text = dayNameFormater.format(events.day.time))
        }
        events.events.forEach {
            EventCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
                    .padding(5.dp),
                event = it
            )
        }
        if (events.events.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
                    .padding(5.dp), contentAlignment = Alignment.Center
            ) {
                Text(text = "Выходной)))")
            }
        }
    }
}