package com.capx.dictionary.ui.screens.dictionary.viewmodel

import app.cash.turbine.test
import com.capx.dictionary.MainDispatcherRule
import com.capx.dictionary.data.entity.DictionaryFts
import com.capx.dictionary.domain.repository.DictionaryRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DictionaryViewmodelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = mockk<DictionaryRepository>(relaxed = true)
    private lateinit var viewModel: DictionaryViewmodel

    private val testLetters = listOf(
        DictionaryFts(1, "A", "en"),
        DictionaryFts(2, "B", "en")
    )

    @Before
    fun setUp() {
        coEvery { repository.getBanglaLetters() } returns testLetters
        coEvery { repository.getEnglishLetters() } returns testLetters
        
        // Mock Paging flows to prevent null pointer or illegal state
        every { repository.getAllBangla(any()) } returns flowOf()
        every { repository.getAllEnglish(any()) } returns flowOf()
        
        viewModel = DictionaryViewmodel(repository)
    }

    @Test
    fun `initial letters are loaded`() = runTest {
        viewModel.banglaLetters.test {
            assertEquals(testLetters, awaitItem())
        }
        viewModel.englishLetters.test {
            assertEquals(testLetters, awaitItem())
        }
    }

    @Test
    fun `changing bangla alpha updates state`() = runTest {
        val newAlpha = DictionaryFts(3, "C", "bn")
        viewModel.changeBanglaAlpha(newAlpha)
        
        viewModel.banglaAlphabet.test {
            assertEquals(newAlpha, awaitItem())
        }
    }

    @Test
    fun `changing english alpha updates state`() = runTest {
        val newAlpha = DictionaryFts(4, "D", "en")
        viewModel.changeEnglishAlpha(newAlpha)
        
        viewModel.englishAlpha.test {
            assertEquals(newAlpha, awaitItem())
        }
    }
}