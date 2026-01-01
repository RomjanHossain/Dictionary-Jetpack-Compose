package com.capx.dictionary.di.repository

import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.domain.repository.DictionaryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DictionaryRepoDi {
    @Provides
    @Singleton
    fun provideDictionaryRepository(dao: DictionaryDao): DictionaryRepository {
        return DictionaryRepository(dao)
    }
}