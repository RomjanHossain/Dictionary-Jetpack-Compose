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


    @Query("select * from bangladic where lower(title) =lower(:query)")
    suspend fun getSelectedWord(query: String): List<DictionaryData>

    @Query("SELECT distinct title FROM bangladic_fts where lower(title) like lower(:query)||'%'")
    fun searchFts(query: String): PagingSource<Int, DictionaryFts>

    @Query("SELECT distinct title FROM bangladic_fts where original_file=='b2b' or original_file=='b2e' and title like :letter or title like upper(:letter)")
    fun getAllbanglaTitles(letter: String): PagingSource<Int, DictionaryFts>

    @Query("SELECT distinct UPPER(substr(title,1,1)) as title FROM bangladic_fts where original_file=='b2b' or original_file=='b2e'")
    suspend fun getAllbanglaLetters(): List<DictionaryFts>

    @Query("SELECT distinct title FROM bangladic_fts where original_file=='e2e' or original_file=='e2b' and title like lower(:letter) or title like upper(:letter)")
    fun getAllenglishTitles(letter: String): PagingSource<Int, DictionaryFts>

    //    SELECT DISTINCT substr(title,1,1) as f from bangladic where original_file=="b2e" or original_file=="b2b";
    @Query("SELECT distinct Upper(substr(title,1,1)) as title FROM bangladic_fts where original_file=='e2e' or original_file=='e2b'")
    suspend fun getAllenglishLetters(): List<DictionaryFts>
}