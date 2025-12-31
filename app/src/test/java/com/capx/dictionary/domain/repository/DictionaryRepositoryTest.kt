package com.capx.dictionary.domain.repository

import app.cash.turbine.test
import com.capx.dictionary.data.dao.DictionaryDao
import com.capx.dictionary.data.entity.DictionaryBookmark
import com.capx.dictionary.data.entity.DictionaryDataDetails
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class DictionaryRepositoryTest {
    private val database = mockk<DictionaryDao>()
    private lateinit var dictionaryRepository: DictionaryRepository

    @Before
    fun setUp() {
        dictionaryRepository = DictionaryRepository(database)
    }

    @Test
    fun `Get single word`() = runTest {
        val words = listOf<DictionaryDataDetails>(
            DictionaryDataDetails(
                id = 0,
                title = "test",
                body = "test",
                originalFile = "test body"
            )
        )
        coEvery { database.getSelectedWord("test") } returns words
        val testWords = dictionaryRepository.getSingleWord("test")
        assertEquals(words, testWords)

        // empty
        coEvery { database.getSelectedWord("empty") } returns emptyList()
        val testWords2 = dictionaryRepository.getSingleWord("empty")
        assertEquals(emptyList<DictionaryDataDetails>(), testWords2)
    }


    @Test
    fun `bookmark testing for some id`() = runTest {
        val id = 3
        val bookmarkFlow = MutableStateFlow(false)
        every { database.getSelectedBookmarkStatus(id) } returns bookmarkFlow
        val gotID = dictionaryRepository.isBookmarked(id)
        assertEquals(gotID, bookmarkFlow)
        bookmarkFlow.value = false
        assertEquals(gotID, bookmarkFlow)
    }

    //    @Test
//    fun getBanglaLetters() {
//    }
//
//    @Test
//    fun getEnglishLetters() {
//    }
//
//    @Test
//    fun getAllBangla() {
//    }
//
//    @Test
//    fun getAllEnglish() {
//    }
//
//    @Test
//    fun deleteBookmark() {
//    }
//
//    @Test
//    fun insertBookmark() {
//    }
//
    @Test
    fun `Get all bookmarks`() = runTest {
        val bookmarks = MutableStateFlow<List<DictionaryBookmark>>(emptyList())

        coEvery { database.getAllBookmarks() } returns bookmarks
        // insert
        coEvery { database.insertBookmark(any()) } answers {
            val b = firstArg<DictionaryBookmark>()
            bookmarks.value += b
        }
        // delete

        coEvery { database.deleteBookmark(any()) } answers {
            val b = firstArg<DictionaryBookmark>()
            bookmarks.value -= b
        }

        dictionaryRepository.getAllBookmarks().test {
            // init
            assertEquals(emptyList<DictionaryBookmark>(), awaitItem())
            // insert
            val bookmark = DictionaryBookmark(
                id = 1,
                title = "word",
                wordID = 1
            )
            dictionaryRepository.insertBookmark(bookmark)
            val listAfterInsert = awaitItem()
            assertEquals(1, listAfterInsert.size)
            // delete
            dictionaryRepository.deleteBookmark(bookmark)
            assertEquals(0, awaitItem().size)
            // cancel all remaining
            cancelAndIgnoreRemainingEvents()

        }

        assertEquals(bookmarks, dictionaryRepository.getAllBookmarks())
        // let's insert one
    }

}