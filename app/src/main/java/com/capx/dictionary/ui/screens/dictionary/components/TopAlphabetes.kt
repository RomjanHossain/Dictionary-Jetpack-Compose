package com.capx.dictionary.ui.screens.dictionary.components

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.capx.dictionary.data.entity.DictionaryFts
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.ui.theme.TabBackgroundDark
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun TopWords(
    letters: List<DictionaryFts>,
    alpha: DictionaryFts?,
    onClick: (alphabate: DictionaryFts) -> Unit
) {
    Column {

        HorizontalDivider(
            thickness = 0.5.dp
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            contentPadding = PaddingValues(horizontal = 5.dp)
        ) {
            items(letters) { alphabet ->
                Card(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            onClick(alphabet)
                        },
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = if (alphabet == alpha) PrimaryColor else MaterialTheme.colorScheme.surfaceVariant
                    ),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            alphabet.title ?: "",
                            style = typography.titleMedium.copy(
                                color = if (alphabet == alpha) Color.White else MaterialTheme.colorScheme.outline
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }

        HorizontalDivider(
            thickness = 0.5.dp
        )
        Spacer(Modifier.height(10.dp))
        if (alpha != null) Text(
            "WORDS STARTING WITH ${alpha.title}",
            modifier = Modifier.padding(start = 15.dp),
            style = TextStyle(color = MaterialTheme.colorScheme.outline)
        )
        Spacer(Modifier.height(10.dp))
    }

}


@ThemePreviews
@Composable
fun PreviewTopWords() {

    val data = listOf<DictionaryFts>(
        DictionaryFts(
            id = 1,
            title = "A",
            source_lang = "bn"
        ), DictionaryFts(
            id = 1,
            title = "B",
            source_lang = "bn"
        )
    )

    DictionaryTheme() {
        TopWords(
            letters = data,
            alpha = data.first(),
        ) { }
    }
}