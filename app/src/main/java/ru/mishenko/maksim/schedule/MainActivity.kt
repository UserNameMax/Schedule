package ru.mishenko.maksim.schedule

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import ru.mishenko.maksim.schedule.data.api.leaderId.model.LeaderIdUrls
import ru.mishenko.maksim.schedule.domain.LeaderAuthUseCase
import ru.mishenko.maksim.schedule.domain.ScheduleUseCase
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.ui.schedule.EventList
import ru.mishenko.maksim.schedule.ui.theme.ScheduleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authUseCase = LeaderAuthUseCase()
            val scheduleUseCase = ScheduleUseCase()
            var events by remember { mutableStateOf(listOf<Event>()) }
            var code by remember { mutableStateOf("") }
            LaunchedEffect(code) {
                if (code != "")
                    authUseCase.auth(schedule = "", code = code)
                events = scheduleUseCase.invoke()
            }
            ScheduleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (code != "")
                        EventList(events = events)
                    else Auth {
                        code = it
                    }
                }
            }
        }
    }
}

@Composable
fun Auth(callback: (String) -> Unit) {
    val redirectUri = "https://localhost"
    val url = LeaderIdUrls.getCodeUrl(apiKey = BuildConfig.leaderApiKey, redirectUri = redirectUri)
    val state = rememberWebViewState(url = url)
    val client = remember {
        object : AccompanistWebViewClient() {
            override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                Log.i("WebView", url ?: "")
                if (url?.startsWith(redirectUri) == true) {
                    callback(url.substringAfter("="))
                } else {
                    super.onPageStarted(view, url, favicon)
                }
            }
        }
    }
    WebView(state = state, onCreated = { it.settings.javaScriptEnabled = true }, client = client)
}