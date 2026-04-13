package com.capx.dictionary.domain.usecase

import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.domain.repository.DictionaryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetWordDetailsUseCaseTest {
    private val repository = mockk<DictionaryRepository>()
    private val useCase = GetWordDetailsUseCase(repository)

    @Test
    fun `invoke should return word details from repository`() = runTest {
        val word = "test"
        val expectedDetails = listOf(
            DictionaryDataDetails(id = 1, title = "test", body = "definition", originalFile = "source")
        )
        coEvery { repository.getSingleWord(word) } returns expectedDetails

        val result = useCase(word)

        assertEquals(expectedDetails, result)
    }
}
