package com.capx.dictionary.di.navigation

import androidx.navigation3.runtime.EntryProviderScope
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
data object HomeScreenKey
data class DetailScreenKey(val kalue: String)

@Module
@InstallIn(ActivityRetainedComponent::class)
object NavigationScreensModule {

    @IntoSet
    @Provides
    fun providesNavigationEntryProviderScopes(navigator: Navigator): EntryProviderInstaller {
        return {
            entry<HomeScreenKey> {
                HomeScreen(
                    onSearch = { it ->
                        navigator.push(
                            DetailScreenKey(
                                kalue = it
                            )
                        )
                    })
            }
            entry<DetailScreenKey> { key ->
                DetailScreen(value = key.kalue, onGoBack = {
                    navigator.pop()
                })
            }
        }

    }
}

