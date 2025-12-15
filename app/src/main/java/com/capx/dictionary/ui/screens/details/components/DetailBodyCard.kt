package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun DetailCardForTrans(modifier: Modifier = Modifier, title: String, content: String) {
    Card(modifier = modifier, shape = CardDefaults.elevatedShape) {
        Text(title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(5.dp))
        Divider()
        Text(content, modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailCardPreview() {
    DetailCardForTrans(
        title = "Bangla",
        content = "THis is content for asdf asdkf"
    )
}
