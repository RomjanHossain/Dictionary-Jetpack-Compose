package com.capx.dictionary.ui.screens.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.utils.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(private val database: DictionaryDao) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeStates>(HomeStates())
    val homeState = _homeState.asStateFlow()

    val searchInput = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchInput
                .debounce { 300L }
                .collectLatest { q ->
                    if (q.isNotEmpty()) {
                        performSearch(q)
                    } else {
                        // clear suggestion
                        _homeState.value = HomeStates(
                            isLoading = false,
                            searchedValues = emptyList()
                        )
                    }
                }
        }
    }

    fun onSearchText(newString: String) {
        _homeState.update { it.copy(searchStr = newString) }
        searchInput.value = newString
    }

    private fun performSearch(q: String) {
        AppLogger.debug("Searching: $q")
        viewModelScope.launch {
            _homeState.update { it.copy(isLoading = true) }
            val result = database.getWordFromTitle(q)
            _homeState.update { it.copy(isLoading = false, searchedValues = result) }
            AppLogger.debug("result: ${result.size}")
        }
    }
}