package com.capx.dictionary.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState.Loading
import androidx.paging.compose.collectAsLazyPagingItems
import com.capx.dictionary.R
import com.capx.dictionary.ui.screens.home.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeSearchField(
    modifier: Modifier = Modifier,
    onSearch: (text: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val text = viewModel.searchInput.collectAsState().value
    val searchResults = viewModel.searchResult.collectAsLazyPagingItems()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column() {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp),
            value = text,
            onValueChange = viewModel::onSearchText,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch(text)
                keyboardController?.hide()
            }),
            maxLines = 1,
//        keyboardActions = KeyboardActions.Default.onSearch,
            prefix = {
                Icon(painter = painterResource(R.drawable.search), "Search Icon")
            },
            trailingIcon = {
                if (searchResults.itemCount != 0 && searchResults.loadState.refresh is Loading) {
                    CircularWavyProgressIndicator(modifier = Modifier.padding(5.dp))
                }

            }
        )
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyColumn() {
                items(searchResults.itemCount) {
                    val curr = searchResults[it]
                    Card(

                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    if (curr?.title != null) {
                                        onSearch(curr.title)
                                    }
                                }),
                    ) {


                        Text(
                            curr?.title ?: "",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
