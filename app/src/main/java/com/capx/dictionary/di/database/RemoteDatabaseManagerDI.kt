package com.capx.dictionary.di.database

import android.content.Context
import com.capx.dictionary.data.source.network.RemoteDatabaseDownloadManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatabaseManagerDI {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android)
    }

    @Provides
    @Singleton
    fun provideRemoteDatabaseManager(
        @ApplicationContext context: Context,
        httpClient: HttpClient
    ): RemoteDatabaseDownloadManager {
        return RemoteDatabaseDownloadManager(context, httpClient)
    }

}