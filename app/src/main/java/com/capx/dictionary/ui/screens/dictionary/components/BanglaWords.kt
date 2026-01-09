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
import com.capx.dictionary.data.entity.DictionaryFts
import com.capx.dictionary.ui.screens.dictionary.viewmodel.DictionaryViewmodel
import com.capx.dictionary.utils.AppLogger

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Banglabody(
    viewmodel: DictionaryViewmodel = hiltViewModel(),
    onSearch: (text: String, id: Int) -> Unit
) {
    val data = viewmodel.banglash.collectAsLazyPagingItems()
    val letters = viewmodel.banglaLetters.collectAsState()
    val alpha = viewmodel.banglaAlphabet.collectAsState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {

        item(span = StaggeredGridItemSpan.FullLine) {

            TopWords(
                alpha = alpha.value,
                letters = letters.value,
            ) {
                viewmodel.changeBanglaAlpha(it)
            }
        }
        items(data.itemCount) {
            val curr: DictionaryFts? = data[it]
            val title = curr?.title ?: ""
            WordCard(
                title = title,
                onSearch = {
                    AppLogger.debug("Bangla word with id: ${curr?.id} and $title || $curr")
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
