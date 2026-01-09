package com.capx.dictionary.ui.screens.Splash.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capx.dictionary.ui.theme.PrimaryColor

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
