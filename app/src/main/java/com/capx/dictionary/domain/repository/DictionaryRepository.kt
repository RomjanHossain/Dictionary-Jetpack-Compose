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

    fun getAllBangla(): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAllbanglaTitles()
            }
        ).flow
    }
    fun getAllEnglish(): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAllenglishTitles()
            }
        ).flow
    }
}