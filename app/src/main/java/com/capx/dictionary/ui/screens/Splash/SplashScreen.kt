package com.capx.dictionary.ui.screens.Splash

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
import com.capx.dictionary.ui.theme.PrimaryColor
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
                    color = MaterialTheme.colorScheme.outline,
                )
            )
        }
        when (state) {
            is SplashScreenStates.Progress -> {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            "Setting up dictionary...",
                            style = TextStyle(fontWeight = FontWeight.W400)
                        )
                        Text(
                            "${state.progress.toInt()}%",
                            style = TextStyle(color = PrimaryColor, fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(Modifier.height(15.dp))
                    ProgressForDownload(state.progress)
                }
            }

            else -> Spacer(Modifier.height(1.dp))
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