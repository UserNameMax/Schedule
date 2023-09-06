package ru.mishenko.maksim.schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mishenko.maksim.schedule.model.Event
import ru.mishenko.maksim.schedule.repository.omgtu.OmgtuRepositoryImpl
import ru.mishenko.maksim.schedule.ui.theme.ScheduleTheme
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var events by remember { mutableStateOf(listOf<Event>()) }
            LaunchedEffect(Unit) {
                events = OmgtuRepositoryImpl().events("ИВТ-213")
            }
            ScheduleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm")
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        events.forEach { event ->
                            Text(text = event.name)
                            Text(
                                text = "${simpleDateFormat.format(event.start.time)} - ${simpleDateFormat.format(event.finish.time)}"
                            )
                            Text(text = event.organizer)
                            Text(text = event.participant)
                            Text(text = event.place)
                            Text(text = event.type)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScheduleTheme {
        Greeting("Android")
    }
}