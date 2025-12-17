package com.capx.dictionary.data.source.network

import android.content.Context
import com.capx.dictionary.utils.AppLogger
import io.ktor.client.HttpClient
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.contentLength
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.readRemaining
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class RemoteDatabaseDownloadManager(
    private val context: Context,
    private val httpClient: HttpClient
) {
    private val READ_ONLY_DB_NAME = "dictionary_db.db"
    private val DOWNLOAD_URL =
        "https://github.com/RomjanHossain/Dictionary-Jetpack-Compose/releases/download/database/database.db"

    fun getReadOnlyDBfile(): File {
        return context.getDatabasePath(READ_ONLY_DB_NAME)
    }

    // This function remains the core entry point for the ViewModel
    fun downloadAndSave(): Flow<Int> = flow {
        val destinationFile = getReadOnlyDBfile()
        val tempFile = File(destinationFile.absolutePath + ".tmp")
        if (tempFile.exists()) tempFile.delete()
        emit(0)

        try {
            httpClient.prepareGet(DOWNLOAD_URL).execute { response ->
                val channel = response.bodyAsChannel()
                val totalSize = response.contentLength() ?: -1L
                var bytesRead = 0L

                // 1. Create a Buffered OutputStream for better performance
                tempFile.outputStream().buffered().use { outputStream ->
                    while (!channel.isClosedForRead) {
                        val packet = channel.readRemaining(max = 8192) // Larger buffer is faster
                        val bytes = packet.readBytes()
                        bytesRead += bytes.size
                        outputStream.write(bytes)

                        if (totalSize > 0) {
                            val progress = (bytesRead * 100 / totalSize).toInt()
                            // 2. We use a channel or simple emission if scoped correctly
                            emit(progress)
                        }
                    }
                }
                if (tempFile.renameTo(destinationFile)) {
                    AppLogger.debug("Successfully downloaded")
                } else {
                    throw Exception("Download failed")
                }
            }
        } catch (e: Exception) {
            AppLogger.err("Download failed", e)
            if (destinationFile.exists()) destinationFile.delete()
            throw e
        }
    }.distinctUntilChanged() // Prevents emitting the same percentage multiple times
        .flowOn(Dispatchers.IO)

}
