package com.capx.dictionary.domain.usecase

import com.capx.dictionary.domain.repository.DictionaryRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ToggleBookmarkUseCaseTest {
    private val repository = mockk<DictionaryRepository>()
    private val useCase = ToggleBookmarkUseCase(repository)

    @Test
    fun `when word is NOT bookmarked, invoke should insert bookmark`() = runTest {
        val wordId = 1
        val title = "test"
        every { repository.isBookmarked(wordId) } returns flowOf(false)
        coEvery { repository.insertBookmark(any()) } returns Unit

        useCase(wordId, title)

        coVerify { repository.insertBookmark(match { it.wordID == wordId && it.title == title }) }
        coVerify(exactly = 0) { repository.deleteBookmarkFromWordID(any()) }
    }

    @Test
    fun `when word IS bookmarked, invoke should delete bookmark`() = runTest {
        val wordId = 1
        val title = "test"
        every { repository.isBookmarked(wordId) } returns flowOf(true)
        coEvery { repository.deleteBookmarkFromWordID(wordId) } returns Unit

        useCase(wordId, title)

        coVerify { repository.deleteBookmarkFromWordID(wordId) }
        coVerify(exactly = 0) { repository.insertBookmark(any()) }
    }
}
