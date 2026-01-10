package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.R
import com.capx.dictionary.ui.screens.details.viewmodels.DetailViewModel
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun BookmarkButton(
    id: Int,
    value: String,
    viewModel: DetailViewModel = hiltViewModel()

) {
    FilledTonalButton(
        onClick = {
            viewModel.toogleBookmark(id, value)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp, start = 10.dp, end = 10.dp),
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = PrimaryColor,
            contentColor = Color.White,
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        viewModel.checkBookmarkStatus(id)
        val isBookmarked = viewModel.isBookmarked.collectAsState()
        Row {
            Icon(
                painter = painterResource(if (isBookmarked.value) R.drawable.bookmarkfill else R.drawable.bookmark),
                "Bookmark this"
            )
            Spacer(Modifier.width(10.dp))
            Text("Bookmark")
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