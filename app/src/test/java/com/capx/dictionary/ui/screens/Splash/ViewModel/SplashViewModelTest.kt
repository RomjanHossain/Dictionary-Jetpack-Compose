package com.capx.dictionary.ui.screens.Splash.ViewModel

import com.capx.dictionary.data.source.network.RemoteDatabaseDownloadManager
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import java.io.File

class SplashViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher();
    private val remoteDatabaseManagerDI = mockk<RemoteDatabaseDownloadManager>();
    private val mockFile = mockk<File>();

    private lateinit var viewModel: SplashViewModel;

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

//    @Test
//    fun `when file not exists`() = runTest { }
//
//    @Test
//    fun `when file exists`() = runTest {
//        every { mockFile.exists() } returns true
//        every { remoteDatabaseManagerDI.getReadOnlyDBfile() } returns mockFile
//
//        viewModel = SplashViewModel(remoteDatabaseManagerDI)
//        viewModel.splashScreenStates.test {
//            assertEquals(SplashScreenStates.Success, awaitItem())
//        }
//    }
}