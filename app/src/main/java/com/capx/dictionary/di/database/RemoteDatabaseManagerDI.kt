package com.capx.dictionary.di.database

import android.content.Context
import com.capx.dictionary.data.source.network.RemoteDatabase
import com.capx.dictionary.data.source.network.RemoteDatabaseDownloadManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatabaseManagerDI {
    @Provides
    @Singleton
    fun provideRemoteDatabaseManager(
        @ApplicationContext context: Context,
        remoteDatabase: RemoteDatabase
    ): RemoteDatabaseDownloadManager {
        return RemoteDatabaseDownloadManager(context, remoteDatabase)
    }
}