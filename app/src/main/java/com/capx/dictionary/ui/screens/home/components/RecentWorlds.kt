package com.capx.dictionary.ui.screens.home.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R

@Composable
fun RecentSearchedWords() {
    // title
    Text(
        stringResource(R.string.recent), style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold
        )
    )
    Spacer(Modifier.height(10.dp))
    // contains
    Text(stringResource(R.string.recent))
    Text(stringResource(R.string.recent))
    Text(stringResource(R.string.recent))
    Text(stringResource(R.string.recent))

}
