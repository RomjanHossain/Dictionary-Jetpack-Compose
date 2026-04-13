package com.capx.dictionary.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.data.entity.DictionaryDataTitles
import com.capx.dictionary.data.entity.DictionaryFts


@Database(
    entities = [
        DictionaryDataTitles::class,
        DictionaryDataDetails::class,
        DictionaryFts::class,
        DictionaryBookmark::class,
    ], version = 1, exportSchema = false
)
abstract class DictionaryDatabaseLocal : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}