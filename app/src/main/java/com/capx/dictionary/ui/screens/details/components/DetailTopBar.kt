package com.capx.dictionary.ui.screens.details.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.capx.dictionary.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(value: String, onGoBack: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onGoBack) {
                Icon(painter = painterResource(R.drawable.nav_back), "Navigation back icon")
            }
        },
        title = {
            Text(value)
        },
    )
}
