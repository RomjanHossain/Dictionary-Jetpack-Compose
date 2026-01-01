package com.capx.dictionary.ui.screens.Splash.ViewModel

sealed class SplashScreenStates {
    data object Idle : SplashScreenStates()
    data class Progress(val progress: Float) : SplashScreenStates()
    data object Success : SplashScreenStates()
    data class Error(val msg: String) : SplashScreenStates()
}