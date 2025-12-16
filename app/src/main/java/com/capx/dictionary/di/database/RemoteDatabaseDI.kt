package com.capx.dictionary.di.database

import com.capx.dictionary.data.source.network.RemoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDatabaseDI {
    private const val URL = "https://github.com/"

    @Provides
    @Singleton
    fun providesOkhttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    fun providesRemoteDatabase(retrofit: Retrofit): RemoteDatabase =
        retrofit.create(RemoteDatabase::class.java)
}