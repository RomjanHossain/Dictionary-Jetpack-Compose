package com.capx.dictionary.ui.screens.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.capx.dictionary.ui.components.GlassCard
import com.capx.dictionary.ui.screens.details.viewmodels.DetailScreenState
import com.capx.dictionary.ui.screens.home.viewmodels.HomeViewModel

@Composable
fun HomeSearchField(
    modifier: Modifier = Modifier,
    onSearch: (text: String, id: Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val text = viewModel.searchInput.collectAsState().value
    val searchResults = viewModel.searchResult.collectAsLazyPagingItems()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        DictionarySearchFields(
            modifier = Modifier.fillMaxWidth(),
            onValueChange = viewModel::onSearchText,
            text = text,
            onSearch = {
                onSearch(text, -1)
                keyboardController?.hide()
            },
            isTrailingIcon = searchResults.itemCount != 0 && searchResults.loadState.refresh is DetailScreenState.Loading,
        )

        Spacer(modifier = Modifier.height(12.dp))

        AnimatedVisibility(
            visible = searchResults.itemCount > 0,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            GlassCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier.height(if (searchResults.itemCount > 5) 300.dp else androidx.compose.ui.unit.Dp.Unspecified)
                ) {
                    items(searchResults.itemCount) { index ->
                        val curr = searchResults[index]
                        SearchResultCard(
                            curr = curr,
                            query = text
                        ) { a, b ->
                            onSearch(a, b)
                        }
                    }
                }
            }
        }
    }
}
