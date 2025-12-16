package com.capx.dictionary.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.entity.DictionaryData


@Database(entities = [DictionaryData::class], version = 1)
abstract class DictionaryDatabaseLocal : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabaseLocal? = null
        fun getInstance(context: Context): DictionaryDatabaseLocal {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabaseLocal::class.java,
                    "dictionary_db.db"
                )
                    .fallbackToDestructiveMigrationOnDowngrade(false) // <-- Use this if you downgrade the version
                    .fallbackToDestructiveMigration(false) // <-- **The simplest temporary fix**
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}