package com.capx.dictionary.ui.screens.Splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.ui.screens.Splash.Components.AppIcon
import com.capx.dictionary.ui.screens.Splash.Components.ProgressForDownload
import com.capx.dictionary.ui.screens.Splash.ViewModel.SplashScreenStates
import com.capx.dictionary.ui.screens.Splash.ViewModel.SplashViewModel
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.utils.ThemePreviews
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinished: () -> Unit, viewModel: SplashViewModel = hiltViewModel()
) {
    val splashState = viewModel.splashScreenStates.collectAsState()


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
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
        modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppIcon()
        Spacer(Modifier.height(32.dp))
        Text(
            "BanglaDict",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Text(
            "Your Bridge Between Languages",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(Modifier.height(64.dp))

        when (state) {
            is SplashScreenStates.Progress -> {
                Column(
                    modifier = Modifier.padding(horizontal = 48.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProgressForDownload(state.progress)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        "Setting up your dictionary... ${state.progress.toInt()}%",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
            else -> {}
        }
    }
}


@ThemePreviews
@Composable
fun SplashBodyPreview() {
    DictionaryTheme() {
        Surface() {
            SplashBody(state = SplashScreenStates.Progress(65f))
        }
    }
}