package com.capx.dictionary.ui.screens.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.capx.dictionary.data.entity.DictionaryFts
import com.capx.dictionary.domain.repository.DictionaryRepository
import com.capx.dictionary.utils.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DictionaryViewmodel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {
    private val _banglaLetters = MutableStateFlow<List<DictionaryFts>>(emptyList())
    private val _banglaAlpha = MutableStateFlow<DictionaryFts?>(null)
    private val _englishLetters = MutableStateFlow<List<DictionaryFts>>(emptyList())
    private val _englishAlpha = MutableStateFlow<DictionaryFts?>(null)

    // states
    val banglaLetters = _banglaLetters.asStateFlow()
    val banglaAlphabet = _banglaAlpha.asStateFlow()
    val englishLetters = _englishLetters.asStateFlow()
    val englishAlpha = _englishAlpha.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val banglash = _banglaAlpha.flatMapLatest { alpha ->
        val searchLetter = alpha?.title ?: ""
        AppLogger.debug("changing letter: $searchLetter")
        dictionaryRepository.getAllBangla(searchLetter)
    }.cachedIn(viewModelScope)

    //    val englishsh =
//        dictionaryRepository.getAllEnglish(englishAlpha.value?.title ?: "").cachedIn(viewModelScope)
    @OptIn(ExperimentalCoroutinesApi::class)
    val englishsh = _englishAlpha.flatMapLatest { alpha ->
        val searchLetter = alpha?.title ?: ""
        dictionaryRepository.getAllEnglish(searchLetter)
    }.cachedIn(viewModelScope)


    init {
        viewModelScope.launch {
            val _bl = dictionaryRepository.getBanglaLetters()
            _banglaLetters.value = _bl

            val _el = dictionaryRepository.getEnglishLetters()
            _englishLetters.value = _el
        }
    }

    // change bangla alpha
    fun changeBanglaAlpha(d: DictionaryFts) {
        _banglaAlpha.value = d;
    }

    // change english alpha
    fun changeEnglishAlpha(d: DictionaryFts) {
        _englishAlpha.value = d;
    }
}
