package com.capx.dictionary.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.entity.DictionaryFts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DictionaryRepository @Inject constructor(
    private val database: DictionaryDao
) {

    // get bangla letters
    suspend fun getBanglaLetters(): List<DictionaryFts> {
        return database.getAllbanglaLetters()
    }

    // get english letters
    suspend fun getEnglishLetters(): List<DictionaryFts> {
        return database.getAllenglishLetters()
    }

    fun getAllBangla(value: String): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAllbanglaTitles(value)
            }
        ).flow
    }

    fun getAllEnglish(value: String): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAllenglishTitles(value)
            }
        ).flow
    }
}