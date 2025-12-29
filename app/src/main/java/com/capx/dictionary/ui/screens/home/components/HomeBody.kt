package com.capx.dictionary.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R

@Composable
fun HomeBody(modifier: Modifier = Modifier, onSearch: (text: String, id: Int) -> Unit) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.app_name), style = MaterialTheme.typography.displayLarge)
        HomeSearchField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            onSearch = onSearch
        )
//        RecentSearchedWords()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeBody() {
    HomeBody(onSearch = {a,b->})
}