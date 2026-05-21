package com.capx.dictionary.ui.screens.bookmark.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.ui.components.GlassCard
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun BookmarkCard(
    word: DictionaryBookmark,
    onDelete: (bookmark: DictionaryBookmark) -> Unit,
    onSearch: (text: String, id: Int) -> Unit
) {
            GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .clickable {
                onSearch(word.title ?: "", word.wordID)
            },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = word.title ?: "", 
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                )
            )
            IconButton(
                onClick = { onDelete(word) },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.bookmarkfill),
                    contentDescription = "Remove Bookmark",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
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
