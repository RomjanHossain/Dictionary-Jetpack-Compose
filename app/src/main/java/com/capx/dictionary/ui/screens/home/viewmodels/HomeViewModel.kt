package com.capx.dictionary.ui.screens.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.entity.DictionaryFts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(private val database: DictionaryDao) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeStates>(HomeStates())
    val homeState = _homeState.asStateFlow()

    val searchInput = MutableStateFlow("")


    val searchResult = searchInput
        .debounce { 300L }
        .distinctUntilChanged()
        .flatMapLatest { q ->
            if (q.isNotEmpty()) {
                getSearchResults(q)
            } else {
                flowOf(PagingData.empty())
            }
        }.cachedIn(viewModelScope)


    fun onSearchText(newString: String) {
        _homeState.update { it.copy(searchStr = newString) }
        searchInput.value = newString
    }


    fun getSearchResults(query: String): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.searchFts(query)
            }
        ).flow
    }
}