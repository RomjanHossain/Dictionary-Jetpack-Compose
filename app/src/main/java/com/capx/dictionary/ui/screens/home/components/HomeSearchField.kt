package com.capx.dictionary.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.capx.dictionary.ui.screens.details.viewmodels.DetailScreenState
import com.capx.dictionary.ui.screens.home.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeSearchField(
    modifier: Modifier = Modifier,
    onSearch: (text: String, id: Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val text = viewModel.searchInput.collectAsState().value
    val searchResults = viewModel.searchResult.collectAsLazyPagingItems()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column() {
        DictionarySearchFields(
            modifier = modifier,
            onValueChange = viewModel::onSearchText,
            text = text,
            onSearch = {
                onSearch(text, -1)
                keyboardController?.hide()
            },
            isTrailingIcon = searchResults.itemCount != 0 && searchResults.loadState.refresh is DetailScreenState.Loading,
        )
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn() {
                items(searchResults.itemCount) {
                    val curr = searchResults[it]
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
