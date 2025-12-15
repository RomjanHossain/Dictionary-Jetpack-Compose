package com.capx.dictionary.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.capx.dictionary.ui.screens.home.components.HomeBody


@Composable
fun HomeScreen(onSearch: (text: String) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        HomeBody(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onSearch = onSearch
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onSearch = {})
}
