package com.capx.dictionary.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.capx.dictionary.ui.screens.bookmark.BookmarkScreen
import com.capx.dictionary.ui.screens.dictionary.DictionaryScreen
import com.capx.dictionary.ui.screens.home.components.BottomNavigationBar
import com.capx.dictionary.ui.screens.home.components.Destinations
import com.capx.dictionary.ui.screens.home.components.HomeBody
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.utils.AppLogger
import com.capx.dictionary.utils.ThemePreviews


@Composable
fun HomeScreen(onSearch: (text: String, id: Int) -> Unit) {
    val navController = rememberNavController()
    val startDestination = Destinations.Home
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar() {
                BottomNavigationBar(
                     currentRoute,
                    onSelect = { _, route ->
                        navController.navigate(route) {
                            AppLogger.info("navigating through navigate")
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination.route
        ) {
            Destinations.entries.forEach { d ->
                composable(d.route) {
                    when (d) {
                        Destinations.Home -> HomeBody(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            onSearch = onSearch
                        )

                        Destinations.Words -> DictionaryScreen(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            onSearch = onSearch
                        )

                        Destinations.Bookmarks -> BookmarkScreen(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize(),
                            onSearch = onSearch
                        )
                    }
                }
            }
        }
    }
}


@ThemePreviews
@Composable
fun HomeScreenPreview() {
    DictionaryTheme() {
        Surface() {
            HomeScreen(onSearch = { a, b -> })
        }
    }
}
