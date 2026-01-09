package com.capx.dictionary.ui.screens.home.components

import android.text.TextUtils
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.copy
import com.capx.dictionary.R
import com.capx.dictionary.ui.theme.PrimaryColor

@Composable
fun HomeBody(modifier: Modifier = Modifier, onSearch: (text: String, id: Int) -> Unit) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Find any word", style = MaterialTheme.typography.displayLarge)
        Text("in seconds", style = MaterialTheme.typography.displayLarge.copy(color = PrimaryColor))
        Text(
            "Translate between English and Bangla instantly",
            style = MaterialTheme.typography.bodyMedium.copy(
                background = MaterialTheme.colorScheme.surface,
                letterSpacing = 1.5.sp,
            ),
            modifier = Modifier.padding(vertical = 10.dp)
        )
        HomeSearchField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            onSearch = onSearch
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeBody() {
    HomeBody(onSearch = { a, b -> })
}