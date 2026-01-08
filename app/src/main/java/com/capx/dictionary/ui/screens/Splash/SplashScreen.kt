package com.capx.dictionary.ui.screens.Splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.ui.screens.Splash.Components.AppIcon
import com.capx.dictionary.ui.screens.Splash.ViewModel.SplashScreenStates
import com.capx.dictionary.ui.screens.Splash.ViewModel.SplashViewModel
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.ThemePreviews
import io.ktor.serialization.Configuration
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
fun SplashBody(modifier: Modifier = Modifier, state: SplashScreenStates) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(1.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppIcon()
            Spacer(Modifier.height(15.dp))
            Text(
                "BanglaDic",
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(5.dp))
            Text(
                "Your Bridge Between Language", style = MaterialTheme.typography.titleMedium.copy(
                    background = MaterialTheme.colorScheme.surface,
                )
            )
        }
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
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("Setting up dictionary...", style = TextStyle(fontWeight = FontWeight.W400))
                        Text(
                            "${state.progress.toInt()}%",
                            style = TextStyle(color = PrimaryColor, fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(Modifier.height(15.dp))
                    ProgressForDownload(state.progress)
                }
            }

            SplashScreenStates.Success -> {
                Text("Welcome To The App", style = MaterialTheme.typography.titleLarge)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProgressForDownload(progress: Float) {
    LinearWavyProgressIndicator(
        progress = { progress / 100 },
        modifier = Modifier
            .fillMaxWidth(),
        gapSize = 0.dp,
        stopSize = 0.dp,
        wavelength = 20.dp,
        color = PrimaryColor
    )
}


@ThemePreviews
@Composable
fun SplashBodyPreview() {
    SplashBody(state = SplashScreenStates.Progress(22.2f))
}