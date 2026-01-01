package com.capx.dictionary.di.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.capx.dictionary.ui.screens.Splash.SplashScreen
import com.capx.dictionary.ui.screens.details.DetailScreen
import com.capx.dictionary.ui.screens.home.HomeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet


// custom type
typealias EntryProviderInstaller = EntryProviderScope<Any>.() -> Unit

// screens
data object SplashScreenKey
data object HomeScreenKey
data class DetailScreenKey(val kalue: String, val id: Int)

@Module
@InstallIn(ActivityRetainedComponent::class)
object NavigationScreensModule {
    @IntoSet
    @Provides
    fun providesNavigationEntryProviderScopes(navigator: Navigator): EntryProviderInstaller {
        return {
            entry<SplashScreenKey> {
                SplashScreen(
                    onFinished = {
                        navigator.pushAndReplace(
                            HomeScreenKey
                        )
                    })
            }
            entry<HomeScreenKey> {
                HomeScreen(
                    onSearch = { a, b ->
                        navigator.push(
                            DetailScreenKey(
                                kalue = a, id = b
                            )
                        )
                    },
                )
            }
            entry<DetailScreenKey> { key ->
                DetailScreen(
                    value = key.kalue, onGoBack = {
                        navigator.pop()
                    }, id = key.id
                )
            }
        }
    }
}

