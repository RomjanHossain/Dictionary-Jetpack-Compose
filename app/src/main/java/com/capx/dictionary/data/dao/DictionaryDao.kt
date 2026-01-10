package com.capx.dictionary.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.data.entity.DictionaryFts
import kotlinx.coroutines.flow.Flow


@Dao
interface DictionaryDao {

    // detail of the word
    @Query("select * from dictionaryDetails where title = :query collate nocase")
    suspend fun getSelectedWord(query: String): List<DictionaryDataDetails>

    ///  search word HOMESCREEN
    @Query("SELECT distinct title, rowid FROM dictionary_fts where lower(title) like lower(:query)||'%'")
    fun searchFts(query: String): PagingSource<Int, DictionaryFts>


    // get all words from the alphabets
    @Query("SELECT distinct title, rowid FROM dictionary_fts where source_lang=='b' and (title like lower(:letter) || '%' or title like upper(:letter) || '%')")
    fun getAllbanglaTitles(letter: String): PagingSource<Int, DictionaryFts>

    // alphabets bangla
    @Query("SELECT distinct UPPER(substr(title,1,1)) as title FROM dictionary_fts where source_lang=='b' ")
    suspend fun getAllbanglaLetters(): List<DictionaryFts>

    // get all words from the alphabets
    @Query("SELECT title,rowid FROM dictionary_fts where source_lang=='e'  and (title like lower(:letter) || '%' or title like upper(:letter) || '%')")
    fun getAllenglishTitles(letter: String): PagingSource<Int, DictionaryFts>

    // alphabets english
    @Query("SELECT distinct Upper(substr(title,1,1)) as title FROM dictionary_fts where source_lang=='e'")
    suspend fun getAllenglishLetters(): List<DictionaryFts>

    // --------------------- bookmark
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: DictionaryBookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: DictionaryBookmark)

    @Query("delete from bookmark where wordID=:id")
    suspend fun deleteBookmarkFromID(id: Int)

    @Query("select * from bookmark order by id desc")
    fun getAllBookmarks(): Flow<List<DictionaryBookmark>>

    @Query("select exists(select * from bookmark where wordID=:id)")
    fun getSelectedBookmarkStatus(id: Int): Flow<Boolean>
}
