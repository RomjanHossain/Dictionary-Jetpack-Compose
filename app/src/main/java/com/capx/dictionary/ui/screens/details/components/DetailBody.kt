package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.layout.Column
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
fun DetailBody(value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(value, style = MaterialTheme.typography.displaySmall)
        DetailCardForTrans(
            title = stringResource(R.string.Bengali),
            content = "This is a placeholder for the actual content",
            modifier = Modifier.padding(vertical = 10.dp)
        )
        DetailCardForTrans(
            title = stringResource(R.string.English),
            content = "This is a placeholder for the actual content"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DetailBodyPreview() {
    DetailBody(value = "Test")
}

