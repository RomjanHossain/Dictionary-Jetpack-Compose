package com.capx.dictionary.ui.screens.bookmark.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.domain.repository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarkViewmodels @Inject constructor(
    private val databaseRepo: DictionaryRepository
) : ViewModel() {
    private val _bookmarks = MutableStateFlow<List<DictionaryBookmark>>(emptyList())

    val bookmarks = _bookmarks.asStateFlow()

    init {
        getAllBookmarks()
    }

    private fun getAllBookmarks() {
        viewModelScope.launch {
            val _bms = databaseRepo.getAllBookmarks();
            _bms.collect {
                _bookmarks.value = it
            }
        }
    }

    fun insert(bookmark: DictionaryBookmark) {
        viewModelScope.launch {
            databaseRepo.insertBookmark(bookmark)
        }
    }

    fun delete(bookmark: DictionaryBookmark) {
        viewModelScope.launch {
            databaseRepo.deleteBookmark(bookmark)
        }
    }

}
