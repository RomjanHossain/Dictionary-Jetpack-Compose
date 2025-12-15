package com.capx.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.capx.dictionary.di.navigation.EntryProviderInstaller
import com.capx.dictionary.di.navigation.Navigator
import com.capx.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var entryProviders: Set<@JvmSuppressWildcards EntryProviderInstaller>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val backStackEntry = remember { mutableStateListOf<Any>(HomeScreenKey) }
            DictionaryTheme {
                NavDisplay(
                    backStack = navigator.backstack,
                    onBack = {
                        navigator.pop()
                    },
                    entryProvider = entryProvider {
                        entryProviders.forEach { builder ->
                            this.builder()
                        }
                    },
                    transitionSpec = {
                        slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                            targetOffsetX = { -it }
                        )
                    },
                    popTransitionSpec = {
                        slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                            targetOffsetX = { it }
                        )
                    }
                )
            }
        }
    }
}

