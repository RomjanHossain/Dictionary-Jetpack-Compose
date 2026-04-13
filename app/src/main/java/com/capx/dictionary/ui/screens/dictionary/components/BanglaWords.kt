package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.capx.dictionary.data.entity.DictionaryFts
import com.capx.dictionary.ui.screens.dictionary.viewmodel.DictionaryViewmodel
import com.capx.dictionary.utils.AppLogger

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
        contentPadding = Arrangement.spacedBy(8.dp).let { 12.dp.let { padding -> androidx.compose.foundation.layout.PaddingValues(start = padding, end = padding, bottom = padding) } },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            TopWords(
                alpha = alpha.value,
                letters = letters.value,
            ) {
                viewmodel.changeBanglaAlpha(it)
            }
        }
        
        items(data.itemCount) { index ->
            val curr: DictionaryFts? = data[index]
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
