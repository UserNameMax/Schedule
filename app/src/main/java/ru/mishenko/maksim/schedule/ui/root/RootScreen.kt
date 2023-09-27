package ru.mishenko.maksim.schedule.ui.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(
    stack: Value<ChildStack<Root.Config, Component>>,
    navigateOnSchedule: () -> Unit,
    navigateOnSettings: () -> Unit
) {
    Scaffold(bottomBar = {
        BottomAppBar {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = navigateOnSchedule) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                    )
                }
                IconButton(onClick = navigateOnSettings) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                    )
                }
            }

        }
    }) {
        Children(modifier = Modifier.padding(it), stack = stack) { child ->
            child.instance.draw()
        }
    }
}