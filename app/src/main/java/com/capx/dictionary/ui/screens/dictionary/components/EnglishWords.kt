package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.capx.dictionary.ui.screens.dictionary.viewmodel.DictionaryViewmodel

@Composable
fun Englishbody(
    viewmodel: DictionaryViewmodel = hiltViewModel(),
    onSearch: (text: String, id: Int) -> Unit
) {
    val data = viewmodel.englishsh.collectAsLazyPagingItems()
    val letters = viewmodel.englishLetters.collectAsState()
    val alpha = viewmodel.englishAlpha.collectAsState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            TopWords(
                letters = letters.value,
                alpha = alpha.value,
            ) {
                viewmodel.changeEnglishAlpha(it)
            }
        }

        items(data.itemCount) { index ->
            val curr = data[index]
            val title = curr?.title ?: ""
            WordCard(
                title = title,
                onSearch = {
                    onSearch(title, curr?.id ?: -1)
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
