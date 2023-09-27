package ru.mishenko.maksim.schedule.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mishenko.maksim.schedule.domain.model.Settings

@Composable
fun SettingsScreen(settings: List<Settings>, onAuth: () -> Unit) {
    Column {
        settings.forEach {
            when (it) {
                is Settings.LeaderIdSettings -> {
                    Text(text = "LeaderId: ${it.userId}")
                }

                is Settings.OmgtuScheduleSettings -> {
                    Text(text = "Поликек: ${it.name}")
                }
            }
        }
        Box(modifier = Modifier.clickable { onAuth() }) {
            Text(text = "Add")
        }
    }
}