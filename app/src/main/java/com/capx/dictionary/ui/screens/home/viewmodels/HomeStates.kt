package com.capx.dictionary.ui.screens.home.viewmodels

import com.capx.dictionary.data.entity.DictionaryData

data class HomeStates(
    val searchStr: String = "",
    val isLoading: Boolean = false,
    val searchedValues: List<DictionaryData> = emptyList()
)
