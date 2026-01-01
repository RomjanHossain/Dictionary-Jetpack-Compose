package com.capx.dictionary.data.source.network

import android.content.Context
import io.ktor.client.HttpClient
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.File

class RemoteDatabaseDownloadManagerTest {
    private val context = mockk<Context>();
    private val httpClient = mockk<HttpClient>();
    private lateinit var rddm: RemoteDatabaseDownloadManager

    @Before
    fun setUp() {
        rddm = RemoteDatabaseDownloadManager(context, httpClient)
    }

    @Test
    fun `get readonly database file tests`() = runTest {
        val READ_ONLY_DB_NAME = "dictionary_db.db"
        val file = File("TmpFile.tmp");

        every { context.getDatabasePath(READ_ONLY_DB_NAME) } returns file
        assertEquals(file, rddm.getReadOnlyDBfile())
    }

}