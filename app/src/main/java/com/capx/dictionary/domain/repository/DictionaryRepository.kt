package com.capx.dictionary.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.data.entity.DictionaryData
import com.capx.dictionary.data.entity.DictionaryFts
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class DictionaryRepository @Inject constructor(
    private val database: DictionaryDao
) {

    /// get single word detail
    suspend fun getSingleWord(word: String): List<DictionaryData> {
        return database.getSelectedWord(word);
    }

    /// check the word is bookmarked or not
    fun isBookmarked(id: Int): Flow<Boolean> {
        return database.getSelectedBookmarkStatus(id)
    }

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
                enablePlaceholders = false,
                prefetchDistance = 10,
            ), pagingSourceFactory = {
                database.getAllbanglaTitles(value)
            }
        ).flow
    }

    fun getAllEnglish(value: String): Flow<PagingData<DictionaryFts>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                prefetchDistance = 10,
                enablePlaceholders = false,
            ), pagingSourceFactory = {
                database.getAllenglishTitles(value)
            }
        ).flow
    }

    // ------------------------- bookmarks
    suspend fun deleteBookmark(bookmark: DictionaryBookmark) {
        database.deleteBookmark(bookmark)
    }

    suspend fun insertBookmark(bookmark: DictionaryBookmark) {
        database.insertBookmark(bookmark)
    }

    fun getAllBookmarks(): Flow<List<DictionaryBookmark>> {
        return database.getAllBookmarks()
    }
}