package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.capx.dictionary.ui.screens.dictionary.viewmodel.DictionaryViewmodel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Banglabody(
    viewmodel: DictionaryViewmodel = hiltViewModel(),
    onSearch: (text: String) -> Unit
) {
    val data = viewmodel.banglash.collectAsLazyPagingItems()
    val letters = viewmodel.banglaLetters.collectAsState()
    val alpha = viewmodel.banglaAlphabet.collectAsState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
    ) {

        item(span = StaggeredGridItemSpan.FullLine) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                contentPadding = PaddingValues(horizontal = 5.dp)
            ) {
                items(letters.value) { alphabet ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (alphabet == alpha.value) MaterialTheme.colorScheme.primary else Color.Transparent
                        )
                    ) {
                        Text(
                            alphabet.title ?: "", style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .clickable {
                                    viewmodel.chanegBanglaAlpha(alphabet)
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
        items(data.itemCount) {
            val curr = data[it]
            val title = curr?.title ?: ""
            WordCard(
                title = title,
                onSearch = {
                    onSearch(title)
                }
            )
        }
        if (data.loadState.refresh == LoadState.Loading) {
            item(span = StaggeredGridItemSpan.FullLine) {
                CenterLoading(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
