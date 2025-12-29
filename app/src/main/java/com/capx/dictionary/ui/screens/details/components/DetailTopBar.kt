package com.capx.dictionary.ui.screens.details.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.R
import com.capx.dictionary.ui.screens.details.viewmodels.DetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    value: String,
    onGoBack: () -> Unit,
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.checkBookmarkStatus(id)
    val isBookmarked = viewModel.isBookmarked.collectAsState()
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onGoBack) {
                Icon(painter = painterResource(R.drawable.nav_back), "Navigation back icon")
            }
        },
        actions = {
            IconButton(onClick = {
                viewModel.toogleBookmark(id, value)
            }) {
                Icon(
                    painter = painterResource(if (isBookmarked.value) R.drawable.bookmarkfill else R.drawable.bookmark),
                    "Bookmark this"
                )
            }
        },
        title = {
            Text(value)
        },
    )
}
