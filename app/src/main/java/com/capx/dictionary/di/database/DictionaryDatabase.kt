package com.capx.dictionary.di.database

import android.content.Context
import androidx.room.Room
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.source.local.DictionaryDatabaseLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DictionaryDatabase {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): DictionaryDatabaseLocal {
        return Room.databaseBuilder(
            context,
            DictionaryDatabaseLocal::class.java,
            "dictionary_db.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDictionaryDatabaseDao(database: DictionaryDatabaseLocal): DictionaryDao {
        return database.dictionaryDao();
    }
}