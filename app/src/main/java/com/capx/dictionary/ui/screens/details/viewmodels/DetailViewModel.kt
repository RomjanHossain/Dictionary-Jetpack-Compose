package com.capx.dictionary.ui.screens.details.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.domain.usecase.GetWordDetailsUseCase
import com.capx.dictionary.domain.usecase.ObserveBookmarkStatusUseCase
import com.capx.dictionary.domain.usecase.ToggleBookmarkUseCase
import com.capx.dictionary.utils.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DetailScreenState {
    data object Loading : DetailScreenState()
    data class Success(val data: List<DictionaryDataDetails>) : DetailScreenState()
    data class Error(val msg: String) : DetailScreenState()
}


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getWordDetailsUseCase: GetWordDetailsUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
    private val observeBookmarkStatusUseCase: ObserveBookmarkStatusUseCase
) : ViewModel() {
    private val _detailState = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    private val _isBookmarked = MutableStateFlow<Boolean>(false)
    val detailState = _detailState.asStateFlow()
    val isBookmarked = _isBookmarked.asStateFlow()


    fun checkBookmarkStatus(id: Int) {
        viewModelScope.launch {
            observeBookmarkStatusUseCase(id).collect {
                _isBookmarked.value = it
            }
        }
    }

    fun toogleBookmark(id: Int, value: String) {
        if (id == -1) return
        viewModelScope.launch {
            try {
                toggleBookmarkUseCase(id, value)
            } catch (e: Exception) {
                AppLogger.err("Failed to toggle bookmark", e)
            }
        }
    }


    fun searchForTheWord(word: String) {
        if (_detailState.value is DetailScreenState.Success) {
            val successData = _detailState.value as DetailScreenState.Success
            if (successData.data.firstOrNull()?.title == word) {
                return
            }
        }
        _detailState.value = DetailScreenState.Loading
        viewModelScope.launch {
            try {
                val results = getWordDetailsUseCase(word)
                _detailState.value = DetailScreenState.Success(results)
            } catch (e: Exception) {
                _detailState.value = DetailScreenState.Error("Error Getting the Word: ${e.localizedMessage}")
            }
        }
    }
}