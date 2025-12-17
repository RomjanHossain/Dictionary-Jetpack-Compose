package com.capx.dictionary.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.capx.dictionary.data.entity.DictionaryData
import com.capx.dictionary.data.entity.DictionaryFts

@Dao
interface DictionaryDao {

    @Query("select * from bangladic where lower(title) like lower(:query)||'%'")
    suspend fun getWordFromTitle(query: String): List<DictionaryData>


    @Query("select * from bangladic where title =:query")
    suspend fun getSelectedWord(query: String): List<DictionaryData>

    // get all b2b
    @Query("select * from bangladic where original_file='b2b'")
    suspend fun getAllb2b(): List<DictionaryData>

    // get all b2e
    @Query("select * from bangladic where original_file='b2e'")
    suspend fun getAllb2e(): List<DictionaryData>

    // get all e2b
    @Query("select * from bangladic where original_file='e2b'")
    suspend fun getAlle2b(): List<DictionaryData>

    // get all e2e
    @Query("select * from bangladic where original_file='e2e'")
    suspend fun getAlle2e(): List<DictionaryData>


    @Query("SELECT title FROM bangladic_fts where lower(title) like lower(:query)||'%'")
    fun searchFts(query: String): PagingSource<Int, DictionaryFts>
}