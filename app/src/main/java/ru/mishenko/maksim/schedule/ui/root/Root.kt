package ru.mishenko.maksim.schedule.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize
import ru.mishenko.maksim.schedule.ui.leaderAuth.LeaderAuth
import ru.mishenko.maksim.schedule.ui.schedule.Schedule
import ru.mishenko.maksim.schedule.ui.settings.Settings

interface Component {
    @Composable
    fun draw()
};

class Root(componentContext: ComponentContext) : Component, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()
    private val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Schedule,
        childFactory = ::child
    )

    private fun child(config: Config, componentContext: ComponentContext): Component =
        when (config) {
            Config.Schedule -> Schedule(
                componentContext = componentContext,
                auth = { pushNavigate(Config.LeaderAuth(it)) })

            is Config.LeaderAuth -> LeaderAuth(
                componentContext = componentContext,
                onDone = {
                    back()
                    config.onDone()
                })

            Config.Settings -> Settings(
                componentContext = componentContext,
                onLeaderAuth = { pushNavigate(Config.LeaderAuth({})) })
        }

    private fun pushNavigate(target: Config) {
        navigation.push(target)
    }

    private fun replaceNavigation(target: Config) {
        navigation.replaceCurrent(target)
    }

    private fun back() {
        navigation.pop()
    }

    sealed interface Config : Parcelable {
        @Parcelize
        object Schedule : Config

        @Parcelize
        data class LeaderAuth(val onDone: () -> Unit) : Config

        @Parcelize
        object Settings : Config

    }

    @Composable
    override fun draw() {
        RootScreen(
            stack = stack,
            navigateOnSchedule = { replaceNavigation(Config.Schedule) },
            navigateOnSettings = { replaceNavigation(Config.Settings) })
    }
}