package com.capx.dictionary

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
            DictionaryTheme {
                AppNavigationDisplay(
                    navigator = navigator,
                    entryProviders = entryProviders
                )
            }
        }
    }
}

@Composable
fun AppNavigationDisplay(
    navigator: Navigator,
    entryProviders: Set<@JvmSuppressWildcards EntryProviderInstaller>
) {

    var lastBackPressTime by remember { mutableLongStateOf(0L) }
    val context = LocalContext.current
    val activity = context as? Activity

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - lastBackPressTime) < 2000) {
            activity?.finish()
        } else {
            lastBackPressTime = currentTime
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }
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