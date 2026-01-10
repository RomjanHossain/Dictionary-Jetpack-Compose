package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.ui.theme.CardColorDark
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.ui.theme.TabSelectThumbDark
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun WordCard(
    title: String,
    onSearch: (title: String) -> Unit,
) {
    val color = if (isSystemInDarkTheme()) TabSelectThumbDark else Color.White
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(enabled = true, onClick = {
                onSearch(title)
            }),
        border = BorderStroke(0.5.dp, PrimaryColor)

    ) {
        Column(
            modifier = Modifier
                .background(color)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(40.dp)
                ) {
                    val twoLetters =
                        if (title.length >= 2) title.slice(IntRange(0, endInclusive = 1)) else title
                    Box(
                        modifier = Modifier
                            .background(color = PrimaryColor.copy(alpha = 0.2f))
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(twoLetters, color = PrimaryColor)
                    }
                }
                Icon(painter = painterResource(R.drawable.arrow), "Arrow")
            }
            Text(
                title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

        }
    }
}

@ThemePreviews
@Composable
fun WordCardPreview() {
    DictionaryTheme {
        WordCard(
            onSearch = { _ -> },
            title = "CharacterLoose"
        )
    }
}