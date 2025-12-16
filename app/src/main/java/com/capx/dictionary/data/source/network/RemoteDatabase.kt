package com.capx.dictionary.data.source.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface RemoteDatabase {
    @Streaming
    @GET
    suspend fun downloadDatabase(@Url fileUrl: String): Response<ResponseBody>
}