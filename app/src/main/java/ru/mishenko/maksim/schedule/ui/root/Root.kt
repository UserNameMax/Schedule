package ru.mishenko.maksim.schedule.ui.root

import android.util.Log
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import kotlinx.parcelize.Parcelize
import ru.mishenko.maksim.schedule.ui.leaderAuth.LeaderAuth
import ru.mishenko.maksim.schedule.ui.schedule.Schedule

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
                auth = { navigate(Config.LeaderAuth(it)) })

            is Config.LeaderAuth -> LeaderAuth(
                componentContext = componentContext,
                onDone = {
                    back()
                    config.onDone()
                })
        }

    private fun navigate(target: Config) {
        navigation.push(target)
    }

    private fun back() {
        navigation.pop()
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object Schedule : Config

        @Parcelize
        data class LeaderAuth(val onDone: () -> Unit) : Config

    }

    @Composable
    override fun draw() {
        Children(stack = stack) {
            Log.i("qweqeqwe", "${it.instance}")
            it.instance.draw()
        }
    }
}