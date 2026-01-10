package com.capx.dictionary.ui.screens.bookmark.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun BookmarkCard(
    word: DictionaryBookmark,
    onDelete: (bookmark: DictionaryBookmark) -> Unit,
    onSearch: (text: String, id: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clickable {
                onSearch(word.title ?: "", word.wordID ?: 0)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                word.title ?: "", style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                )
            )
            Icon(
                painterResource(R.drawable.bookmarkfill),
                "Bookmarked",
                modifier = Modifier.clickable {
                    // remove the word
                    onDelete(word)
                },
                tint = PrimaryColor,
            )

        }
    }
}

@ThemePreviews
@Composable
fun PreviewBOokmarkCard() {
    BookmarkCard(
        DictionaryBookmark(
            id = 3,
            title = "HOLY",
            wordID = 23
        ),
        onDelete = {},
        onSearch = { a, b -> }
    )
}
