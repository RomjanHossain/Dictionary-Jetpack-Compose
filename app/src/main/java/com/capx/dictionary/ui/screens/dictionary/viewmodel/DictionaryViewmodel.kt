package com.capx.dictionary.ui.screens.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.capx.dictionary.domain.repository.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DictionaryViewmodel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {
    val banglash = dictionaryRepository.getAllBangla().cachedIn(viewModelScope)
    val englishsh = dictionaryRepository.getAllEnglish().cachedIn(viewModelScope)
}