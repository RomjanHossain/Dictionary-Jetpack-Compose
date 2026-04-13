package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capx.dictionary.data.entity.DictionaryFts
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun TopWords(
    letters: List<DictionaryFts>,
    alpha: DictionaryFts?,
    onClick: (alphabate: DictionaryFts) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(letters) { alphabet ->
                val selected = alphabet == alpha
                FilterChip(
                    selected = selected,
                    onClick = { onClick(alphabet) },
                    label = {
                        Text(
                            text = alphabet.title ?: "",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                            )
                        )
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                        labelColor = MaterialTheme.colorScheme.primary,
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    border = null
                )
            }
        }

        if (alpha != null) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Words starting with \"${alpha.title}\"".uppercase(),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = androidx.compose.ui.unit.TextUnit.Unspecified
                )
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}


@ThemePreviews
@Composable
fun PreviewTopWords() {
    val data = listOf(
        DictionaryFts(id = 1, title = "A", source_lang = "bn"),
        DictionaryFts(id = 2, title = "B", source_lang = "bn"),
        DictionaryFts(id = 3, title = "C", source_lang = "bn")
    )

    DictionaryTheme {
        TopWords(
            letters = data,
            alpha = data.first(),
        ) { }
    }
}