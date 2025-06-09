package com.giraffe.myweatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.giraffe.myweatherapp.presentation.HomeScreen
import com.giraffe.myweatherapp.presentation.HomeViewModel
import com.giraffe.myweatherapp.ui.theme.MyWeatherAppTheme
import org.koin.androidx.compose.koinViewModel

val LocalActivity = staticCompositionLocalOf<ComponentActivity> { error("LocalContext") }

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = koinViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()
            MyWeatherAppTheme(darkTheme = !state.isDay) {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    CompositionLocalProvider(LocalActivity provides this) {
                        HomeScreen()
                    }
                    //PlaygroundScreen()
                }
            }
        }
    }
}