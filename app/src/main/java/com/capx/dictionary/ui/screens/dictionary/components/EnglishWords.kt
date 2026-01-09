package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.capx.dictionary.ui.screens.dictionary.viewmodel.DictionaryViewmodel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            TopWords(
                letters = letters.value,
                alpha = alpha.value,
            ) {
                viewmodel.changeEnglishAlpha(it)
            }
        }

        items(data.itemCount) {
            val curr = data[it]

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
