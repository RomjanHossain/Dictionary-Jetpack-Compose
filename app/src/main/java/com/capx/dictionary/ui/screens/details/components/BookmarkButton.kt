package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.R
import com.capx.dictionary.ui.components.GlassButton
import com.capx.dictionary.ui.screens.details.viewmodels.DetailViewModel
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun BookmarkButton(
    id: Int,
    value: String,
    viewModel: DetailViewModel = hiltViewModel()

) {
    viewModel.checkBookmarkStatus(id)
    val isBookmarked = viewModel.isBookmarked.collectAsState()
    
    GlassButton(
        onClick = {
            viewModel.toogleBookmark(id, value)
        },
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable { viewModel.toogleBookmark(id, value) },
        containerColor = if (isBookmarked.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    ) {
        Row {
            Icon(
                painter = painterResource(if (isBookmarked.value) R.drawable.bookmarkfill else R.drawable.bookmark),
                contentDescription = "Bookmark this",
                tint = if (isBookmarked.value) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = if (isBookmarked.value) "Bookmarked" else "Save to Bookmarks",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isBookmarked.value) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}


@ThemePreviews
@Composable
fun BookmarkButtonPreview() {
    BookmarkButton(
        id = 1, value = "TEst",
    )
}