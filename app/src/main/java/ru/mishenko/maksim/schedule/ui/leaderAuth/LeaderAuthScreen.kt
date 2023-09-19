package ru.mishenko.maksim.schedule.ui.leaderAuth

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import ru.mishenko.maksim.schedule.BuildConfig
import ru.mishenko.maksim.schedule.data.api.leaderId.model.LeaderIdUrls

@Composable
fun LeaderAuthScreen(callback: (String) -> Unit) {
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