package com.mobile.pvnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.mobile.pvnews.presentation.base.NewsNavHost
import com.mobile.pvnews.presentation.theme.PVNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            PVNewsTheme {
                NewsNavHost()
            }
        }
    }
}