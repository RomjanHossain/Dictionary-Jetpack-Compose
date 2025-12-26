package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
    ) {
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
