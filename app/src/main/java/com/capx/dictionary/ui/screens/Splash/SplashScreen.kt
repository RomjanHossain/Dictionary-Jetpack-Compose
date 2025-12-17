package com.capx.dictionary.ui.screens.Splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.ui.screens.Splash.ViewModel.SplashScreenStates
import com.capx.dictionary.ui.screens.Splash.ViewModel.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinished: () -> Unit, viewModel: SplashViewModel = hiltViewModel()
) {
    val splashState = viewModel.splashScreenStates.collectAsState()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val state = splashState.value
        LaunchedEffect(state) {
            if (state is SplashScreenStates.Success) {
                delay(300)
                onFinished()
            }
        }
        SplashBody(modifier = Modifier.padding(innerPadding), state = state)
    }

}

@Composable
fun SplashBody(modifier: Modifier, state: SplashScreenStates) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (state) {
            is SplashScreenStates.Error -> {
                Text(
                    state.msg,
                    style = TextStyle(fontStyle = FontStyle.Italic, textAlign = TextAlign.Center)
                )
            }

            SplashScreenStates.Idle -> {
                Text("..............")
            }

            is SplashScreenStates.Progress -> {
                Text("Downloading...", textAlign = TextAlign.Center)
                ProgressForDownload(state.progress)
            }

            SplashScreenStates.Success -> {
                Text("Download Done")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProgressForDownload(progress: Float) {
    LinearWavyProgressIndicator(
        progress = { progress / 100 },
        modifier = Modifier.fillMaxWidth(),
    )
}