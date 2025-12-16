package com.capx.dictionary.data.source.network

import android.content.Context
import com.capx.dictionary.utils.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import java.io.File

class RemoteDatabaseDownloadManager(
    private val context: Context, private val remoteDatabase: RemoteDatabase
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
        emit(0) // Emit 0% immediately

        try {
            // 1. NETWORK CALL (Suspend)
            val response = remoteDatabase.downloadDatabase(DOWNLOAD_URL)

            if (response.isSuccessful) {
                val body = response.body() ?: throw IOException("Response body is Null")

                // --- START OF INLINED I/O LOGIC (from saveDatabaseFile) ---

                // Ensure parent directories exist
                destinationFile.parentFile?.mkdirs()

                val totalBytes = body.contentLength()
                var bytesRead = 0L
                val isIndeterminate = totalBytes <= 0

                body.byteStream().use { inputStream ->
                    destinationFile.outputStream().use { outputStream ->

                        val buffer = ByteArray(8192)
                        var read: Int
                        var lastPercentage = -1

                        // Loop through the stream, reading chunks and reporting progress
                        while (inputStream.read(buffer).also { read = it } != -1) {
                            outputStream.write(buffer, 0, read)
                            bytesRead += read

                            // 2. PROGRESS CALCULATION AND EMISSION
                            if (!isIndeterminate) {
                                val currentPercentage = ((bytesRead * 100) / totalBytes).toInt()
                                if (currentPercentage > lastPercentage) {
                                    // 'emit' is safely called here within the flow builder's scope
                                    emit(currentPercentage)
                                    lastPercentage = currentPercentage
                                }
                            }
                        }
                    }
                }
                // --- END OF INLINED I/O LOGIC ---

                emit(100) // Final success emission

            } else {
                throw IOException("Response failed. Code: ${response.code()}")
            }
        } catch (e: Exception) {
            AppLogger.err("Download and Save Failed", e)
            destinationFile.delete() // Clean up incomplete file
            throw e // Re-throw to be caught by the ViewModel
        }
    }.flowOn(Dispatchers.IO) // Ensure ALL network and file I/O runs on the IO thread

    // REMOVE saveDatabaseFile ENTIRELY
}
