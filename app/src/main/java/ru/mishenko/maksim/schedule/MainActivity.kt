package ru.mishenko.maksim.schedule

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
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import ru.mishenko.maksim.schedule.data.repository.omgtu.OmgtuRepositoryImpl
import ru.mishenko.maksim.schedule.domain.ScheduleUseCase
import ru.mishenko.maksim.schedule.domain.model.Event
import ru.mishenko.maksim.schedule.ui.schedule.EventList
import ru.mishenko.maksim.schedule.ui.theme.ScheduleTheme

class MainActivity : ComponentActivity() {
    @OptIn(InternalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var events by remember { mutableStateOf(listOf<Event>()) }
            var code by remember { mutableStateOf("") }
            LaunchedEffect(Unit) {
                events = ScheduleUseCase().invoke()
                events = OmgtuRepositoryImpl().events("ИВТ-213")
            }
            LaunchedEffect(code) {
                val client = HttpClient(CIO)
                if (code != "") {
                    Log.i("WebView", "code = $code")
                    val response = client.post("https://apps.leader-id.ru/api/v1/oauth/token") {
                        val body = "{\n" +
                                "    \"client_id\": \"${BuildConfig.leaderApiKey}\",\n" +
                                "    \"client_secret\": \"${BuildConfig.leaderIdSecret}\",\n" +
                                "    \"grant_type\": \"authorization_code\",\n" +
                                "    \"code\": \"$code\"\n" +
                                "}"
                        Log.i("WebView", body)
                        contentType(ContentType.Application.Json)
                        setBody(body)
                    }
                    Log.i("WebView", "$response")
                    Log.i("WebView", "${response.content.readUTF8Line(10000)}")
                }

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
    //https://leader-id.ru/apps/authorize?client_id=28854ddd-3f47-4c3b-9bbc-be070c000ccd&redirect_uri=https://localhost&response_type=code
    val redirectUri = "https://localhost"
    val state =
        rememberWebViewState(url = "https://leader-id.ru/apps/authorize?client_id=${BuildConfig.leaderApiKey}&redirect_uri=${redirectUri}&response_type=code")
    val client = remember {
        object : AccompanistWebViewClient() {
            override fun onPageFinished(view: WebView, url: String?) {
                super.onPageFinished(view, url)
                Log.i("WebView", "$url")
                if (url?.startsWith(redirectUri) == true) {
                    callback(url.substringAfter("="))
                }
            }
        }
    }
    WebView(state = state, onCreated = { it.settings.javaScriptEnabled = true }, client = client)
}