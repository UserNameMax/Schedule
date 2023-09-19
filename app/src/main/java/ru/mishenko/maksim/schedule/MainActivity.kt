package ru.mishenko.maksim.schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.mishenko.maksim.schedule.ui.root.Root
import ru.mishenko.maksim.schedule.ui.theme.ScheduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = Root(defaultComponentContext())
        setContent {
            ScheduleTheme {
                root.draw()
            }
        }
    }
}