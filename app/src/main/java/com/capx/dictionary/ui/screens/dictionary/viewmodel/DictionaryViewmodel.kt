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
    val e2e = dictionaryRepository.getE2e().cachedIn(viewModelScope)
    val e2b = dictionaryRepository.getE2b().cachedIn(viewModelScope)
    val b2e = dictionaryRepository.getB2e().cachedIn(viewModelScope)
    val b2b = dictionaryRepository.getB2b().cachedIn(viewModelScope)
}