package ru.mishenko.maksim.schedule.ui.schedule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.mishenko.maksim.schedule.domain.ScheduleUseCase
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.ui.root.Component

class Schedule(componentContext: ComponentContext) : Component,
    ComponentContext by componentContext {
    private val scheduleUseCase = ScheduleUseCase()
    private val mutableState = MutableStateFlow(listOf<Event>())
    private val scope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default) //TODO research CoroutineScope in decompose

    init {
        update()
    }

    fun update() = scope.launch {
        mutableState.update { scheduleUseCase() }
    }

    @Composable
    override fun draw() {
        val state by mutableState.collectAsState()
        ScheduleScreen(events = state)
    }
}