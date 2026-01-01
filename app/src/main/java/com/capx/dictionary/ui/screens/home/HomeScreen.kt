package com.capx.dictionary.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capx.dictionary.R
import com.capx.dictionary.ui.screens.bookmark.BookmarkScreen
import com.capx.dictionary.ui.screens.dictionary.DictionaryScreen
import com.capx.dictionary.ui.screens.home.components.HomeBody


enum class Destinations(
    val route: String,
    val label: String,
    val image: Int,
    val contentDescription: String
) {
    Home("home", "Home", R.drawable.home, "Home Icon"),
    Words("dictionary", "Dictionary", R.drawable.list, "list of all words"),
    Bookmarks("bookmark", "Bookmark", R.drawable.bookmark, "bookmark icon"),
}

@Composable
fun HomeScreen(onSearch: (text: String, id: Int) -> Unit) {
    val navController = rememberNavController()
    val startDestination = Destinations.Home
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar() {
                NavigationBar() {
                    Destinations.entries.forEachIndexed { i, d ->
                        NavigationBarItem(
                            selected = i == selectedDestination,
                            label = {
                                Text(d.label)
                            },
                            icon = {
                                Icon(painter = painterResource(d.image), d.contentDescription)
                            },
                            onClick = {
                                navController.navigate(d.route)
                                selectedDestination = i
                            }
                        )
                    }

                }

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

@Composable
fun NavigationBody() {

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onSearch = { a, b -> })
}
