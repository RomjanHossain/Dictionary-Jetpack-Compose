package com.capx.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.capx.dictionary.ui.navigations.DetailScreenKey
import com.capx.dictionary.ui.navigations.HomeScreenKey
import com.capx.dictionary.ui.theme.DictionaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val backStackEntry = remember { mutableStateListOf<Any>(HomeScreenKey) }
            DictionaryTheme {
                NavDisplay(
                    backStack = backStackEntry,
                    onBack = {
                        backStackEntry.removeLastOrNull()
                    },
                    entryProvider = entryProvider {
                        HomeScreenKey(backStackEntry)
                        DetailScreenKey(backStackEntry)
                    },
                    transitionSpec = {
                        slideInHorizontally(initialOffsetX = {it}) togetherWith slideOutHorizontally(
                            targetOffsetX = {-it}
                        )
                    },
                    popTransitionSpec = {
                        slideInHorizontally(initialOffsetX = {-it}) togetherWith slideOutHorizontally(
                            targetOffsetX = {it}
                        )
                    }
                )
            }
        }
    }
}

