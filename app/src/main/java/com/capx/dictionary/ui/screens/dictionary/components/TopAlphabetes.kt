package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.capx.dictionary.data.entity.DictionaryFts

@Composable
fun TopWords(
    letters: List<DictionaryFts>,
    alpha: DictionaryFts?,
    onClick: (alphabate: DictionaryFts) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(horizontal = 5.dp)
    ) {
        items(letters) { alphabet ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (alphabet == alpha) MaterialTheme.colorScheme.primary else Color.Transparent
                )
            ) {
                Text(
                    alphabet.title ?: "", style = typography.titleMedium,
                    modifier = Modifier
                        .clickable {
                            onClick(alphabet)
                        }
                        .padding(8.dp)
                )
            }
        }
    }

}
