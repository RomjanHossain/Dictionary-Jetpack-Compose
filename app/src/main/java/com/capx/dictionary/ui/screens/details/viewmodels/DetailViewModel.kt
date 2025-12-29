package com.capx.dictionary.ui.screens.details.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.data.entity.DictionaryData
import com.capx.dictionary.domain.repository.DictionaryRepository
import com.capx.dictionary.utils.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DetailScreenState {
    data object Loading : DetailScreenState()
    data class Success(val data: List<DictionaryData>) : DetailScreenState()
    data class Error(val msg: String) : DetailScreenState()
}


@HiltViewModel
class DetailViewModel @Inject constructor(
//    private val database: DictionaryDao,
    private val dictionaryRepository: DictionaryRepository
) :
    ViewModel() {
    private val _detailState = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    private val _isBookmarked = MutableStateFlow<Boolean>(false)
    val detailState = _detailState.asStateFlow()
    val isBookmarked = _isBookmarked.asStateFlow()


    fun checkBookmarkStatus(id: Int) {
        viewModelScope.launch {
            val _isB = dictionaryRepository.isBookmarked(id)
            _isB.collect {
                _isBookmarked.value = it
            }
        }
    }

    fun toogleBookmark(id: Int, value: String) {
        AppLogger.info("Toggling: $id with $value")
        if (id == -1) return;
        if (_isBookmarked.value) {
            // remove from bookmark & make it false
            _isBookmarked.value = false

        } else {
            viewModelScope.launch {
                dictionaryRepository.insertBookmark(
                    DictionaryBookmark(
                        title = value,
                        wordID = id
                    )
                )
            }
            _isBookmarked.value = false
        }
    }


    fun searchForTheWord(word: String) {
        if (_detailState.value is DetailScreenState.Success) {
            val _data = _detailState.value as DetailScreenState.Success
            if (_data.data.firstOrNull()?.title == word) {
                return
            }
        }
        _detailState.value = DetailScreenState.Loading
        viewModelScope.launch {
            try {
                val results = dictionaryRepository.getSingleWord(word)
                _detailState.value = DetailScreenState.Success(results)
            } catch (e: Exception) {
                _detailState.value = DetailScreenState.Error("Error Getting the Word, $e")
            }
        }
    }
}