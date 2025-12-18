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

    fun getB2b(): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAllb2b()
            }
        ).flow
    }

    fun getB2e(): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAllb2e()
            }
        ).flow
    }

    fun getE2b(): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAlle2b()
            }
        ).flow
    }

    fun getE2e(): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {
                database.getAlle2e()
            }
        ).flow
    }

}