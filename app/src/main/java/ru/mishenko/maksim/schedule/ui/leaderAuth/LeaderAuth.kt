package ru.mishenko.maksim.schedule.ui.leaderAuth

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mishenko.maksim.schedule.domain.LeaderAuthUseCase
import ru.mishenko.maksim.schedule.ui.root.Component

class LeaderAuth(componentContext: ComponentContext, private val onDone: () -> Unit) : Component,
    ComponentContext by componentContext {
    private val authUseCase = LeaderAuthUseCase()
    private val scope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default) //TODO research CoroutineScope in decompose

    private fun auth(code: String) = scope.launch {
        authUseCase.auth("", code)
        withContext(Dispatchers.Main) { onDone() }
    }

    @Composable
    override fun draw() {
        LeaderAuthScreen(callback = ::auth)
    }
}