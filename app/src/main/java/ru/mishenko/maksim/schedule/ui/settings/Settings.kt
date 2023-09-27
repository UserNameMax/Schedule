package ru.mishenko.maksim.schedule.ui.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.mishenko.maksim.schedule.domain.GetSettingsUseCase
import ru.mishenko.maksim.schedule.ui.root.Component

class Settings(componentContext: ComponentContext, private val onLeaderAuth: () -> Unit) :
    Component,
    ComponentContext by componentContext, KoinComponent {
    private val getSettingsUseCase: GetSettingsUseCase by inject()
    private val settings = MutableStateFlow(getSettingsUseCase.getSettings("default"))

    private fun leaderAuth() {
        onLeaderAuth()
        settings.update { getSettingsUseCase.getSettings("default") }
    }

    @Composable
    override fun draw() {
        val settings by this.settings.collectAsState()
        SettingsScreen(settings = settings, onAuth = ::leaderAuth)
    }
}