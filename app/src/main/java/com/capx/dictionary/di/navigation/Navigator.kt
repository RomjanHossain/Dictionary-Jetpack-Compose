package com.capx.dictionary.di.navigation

import androidx.compose.runtime.mutableStateListOf
import dagger.hilt.android.scopes.ActivityRetainedScoped


@ActivityRetainedScoped
class Navigator(startDestination: Any) {
    val backstack = mutableStateListOf(startDestination)

    fun push(newDestination: Any) {
        backstack.add(newDestination)
    }

    fun pop() {
        backstack.removeLastOrNull()
    }
}