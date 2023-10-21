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
import java.util.Calendar
import java.util.GregorianCalendar

class Schedule(componentContext: ComponentContext) : Component,
    ComponentContext by componentContext {
    private val scheduleUseCase = ScheduleUseCase()
    private val mutableState = MutableStateFlow(listOf<EventListUi>())
    private val scope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default) //TODO research CoroutineScope in decompose
    private val today by lazy {
        GregorianCalendar().apply {
            add(Calendar.MINUTE, -(get(Calendar.MINUTE) + 60 * get(Calendar.HOUR)))
        }
    }
    private val dayCount = 31

    private fun today() = today.clone() as Calendar

    init {
        load()
    }

    private fun load() = scope.launch {
        val finish = today().apply { add(Calendar.DAY_OF_MONTH, dayCount) }
        mutableState.update { scheduleUseCase(start = today, finish = finish).toUi() }
    }

    private fun List<Event>.toUi(): List<EventListUi> = List(dayCount) { index ->
        val day = today().apply { add(Calendar.DAY_OF_MONTH, index) }
        val nextDay = today().apply { add(Calendar.DAY_OF_MONTH, index + 1) }
        val events = filter { it.start in day..nextDay }
        EventListUi(day = day, events = events)
    }

    @Composable
    override fun draw() {
        val state by mutableState.collectAsState()
        ScheduleScreen(events = state)
    }
}