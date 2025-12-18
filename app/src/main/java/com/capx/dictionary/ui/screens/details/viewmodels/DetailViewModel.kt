package com.capx.dictionary.ui.screens.details.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.entity.DictionaryData
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
    private val database: DictionaryDao,
) :
    ViewModel() {


    private val _detailState = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val detailState = _detailState.asStateFlow()


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
                val results = database.getSelectedWord(word)
                _detailState.value = DetailScreenState.Success(results)
            } catch (e: Exception) {
                _detailState.value = DetailScreenState.Error("Error Getting the Word, $e")
            }
        }
    }
}