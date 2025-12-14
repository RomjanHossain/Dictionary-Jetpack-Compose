package com.capx.dictionary.ui.navigations

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.capx.dictionary.ui.screens.details.DetailScreen
import com.capx.dictionary.ui.screens.home.HomeScreen

data object HomeScreenKey: NavKey
data class DetailScreenKey(val kalue:String): NavKey

fun EntryProviderScope<Any>.DetailScreenKey() {
    entry<DetailScreenKey> { key ->
        DetailScreen(value = key.kalue)
    }
}

fun EntryProviderScope<Any>.HomeScreenKey(backStackEntry: SnapshotStateList<Any>) {
    entry<HomeScreenKey> {
        HomeScreen(
            onSearch = {it->
                backStackEntry.add(DetailScreenKey(
                    kalue = it
                )
                )
            }
        )
    }
}
