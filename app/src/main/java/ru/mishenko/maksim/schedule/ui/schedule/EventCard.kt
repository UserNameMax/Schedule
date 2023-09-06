package ru.mishenko.maksim.schedule.ui.schedule

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mishenko.maksim.schedule.model.Event
import java.text.SimpleDateFormat

@Composable
fun EventCard(modifier: Modifier = Modifier, event: Event) {
    val dateFormater = SimpleDateFormat("yyyy.MM.dd")
    val timeFormater = SimpleDateFormat("HH:mm")
    Row(
        modifier = modifier
            .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
            .padding(5.dp)
    ) {
        Column {
            Text(text = dateFormater.format(event.start.time))
            Text(text = "${timeFormater.format(event.start.time)} - ${timeFormater.format(event.finish.time)}")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = event.name)
            Text(text = event.organizer)
            Text(text = event.participant)
            Row {
                Text(text = event.type.split(" ").map { it.first().toString() }
                    .reduce { acc, c -> acc + c })
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = event.place)
            }
        }
    }
}