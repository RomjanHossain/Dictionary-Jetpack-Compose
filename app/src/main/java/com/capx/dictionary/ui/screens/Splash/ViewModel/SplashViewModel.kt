package com.capx.dictionary.ui.screens.Splash.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capx.dictionary.data.source.network.RemoteDatabaseDownloadManager
import com.capx.dictionary.utils.AppLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val remoteDatabaseManagerDi: RemoteDatabaseDownloadManager
) :
    ViewModel() {

    private val _splashScreenStates = MutableStateFlow<SplashScreenStates>(SplashScreenStates.Idle)

    val splashScreenStates = _splashScreenStates.asStateFlow()

    init {
        startDownload()
    }

    fun startDownload() {
        AppLogger.debug("start downloading................")

        // 1. File existence check (Remains correct)
        val file = remoteDatabaseManagerDi.getReadOnlyDBfile()
        if (file.exists()) {
            _splashScreenStates.value = SplashScreenStates.Success
            AppLogger.debug("File already exists")
            return
        }

        AppLogger.debug("File not exists - Starting download coroutine")

        viewModelScope.launch {
            AppLogger.debug("started viewmodel scope launch")

            // Removed redundant Progress(0f) state, relying on the Flow to emit it first.

            try {
                // 2. Start collecting the progress stream
                remoteDatabaseManagerDi.downloadAndSave().collect { progress ->
                    AppLogger.debug("progress value: $progress")

                    // Update state on every emission (0% to 99%)
                    _splashScreenStates.value =
                        SplashScreenStates.Progress(progress = progress.toFloat())
                }

                // 3. SUCCESS TRANSITION: Executed only when the Flow terminates successfully (after 100% emission)
                _splashScreenStates.value = SplashScreenStates.Success
                AppLogger.debug("Download completed successfully and state transitioned to Success.")

            } catch (e: Exception) {
                // 4. ERROR HANDLING: Executed if the network or disk I/O fails
                AppLogger.err("Error on downloading or saving file", e)
                _splashScreenStates.value =
                    SplashScreenStates.Error("Error Downloading file: ${e.message}")
            }
        }

        AppLogger.debug("Viewmodel launch DONE")
    }


}