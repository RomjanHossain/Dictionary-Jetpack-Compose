package com.capx.dictionary.ui.screens.bookmark

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.R
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.ui.screens.bookmark.viewmodel.BookmarkViewmodels
import com.capx.dictionary.utils.AppLogger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier, onSearch: (text: String, id: Int) -> Unit,
    viewModel: BookmarkViewmodels = hiltViewModel()
) {
    val bookmarks = viewModel.bookmarks.collectAsState()
    Column() {
        TopAppBar(
            title = {
                Text(stringResource(R.string.Bookmark))
            }
        )

        if (bookmarks.value.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("No Bookmarks Found!!", textAlign = TextAlign.Center)
            }
        } else {

            LazyColumn() {
                items(bookmarks.value.size) {
                    val bookmark = bookmarks.value[it]
                    AppLogger.info("Current bookmark: ${bookmark.title} with ${bookmark.id}")
                    BookmarkCard(bookmark, onSearch = onSearch, onDelete = {
                        viewModel.delete(bookmark)
                    })

                }
            }
        }
    }


}


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
                onSearch(word.title ?: "", word.id ?: 0)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(word.title ?: "")
            Icon(
                painterResource(R.drawable.bookmarkfill),
                "Bookmarked",
                modifier = Modifier.clickable {
                    // remove the word
                    onDelete(word)
                })
        }
    }
}

@Preview(showBackground = true)
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


