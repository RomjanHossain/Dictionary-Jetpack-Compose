package com.capx.dictionary.ui.screens.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capx.dictionary.ui.screens.details.components.DetailBody
import com.capx.dictionary.ui.screens.details.components.DetailTopBar
import com.capx.dictionary.utils.AppLogger

@Composable
fun DetailScreen(
    value: String, onGoBack: () -> Unit, id: Int
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), topBar = {
            DetailTopBar(
                value = value,
                onGoBack = onGoBack,
//                id = id,
            )
        }) { innerPadding ->
        DetailBody(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            value = value,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(value = "Hello", onGoBack = {}, 0)
}
