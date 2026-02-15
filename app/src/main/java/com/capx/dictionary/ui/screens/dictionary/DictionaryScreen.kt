package com.capx.dictionary.ui.screens.dictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capx.dictionary.ui.screens.dictionary.components.Banglabody
import com.capx.dictionary.ui.screens.dictionary.components.DictionaryTopAppBar
import com.capx.dictionary.ui.screens.dictionary.components.Englishbody

enum class TabDestinations(
    val route: String,
    val label: String,
) {
    Bangla("bangla", "Bangla"),
    English("english", "English"),
}

@Composable
fun DictionaryScreen(
    modifier: Modifier = Modifier, onSearch: (text: String, id: Int) -> Unit
) {
    val navController = rememberNavController()
    val startDestination = TabDestinations.Bangla
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Column(
        modifier = modifier,
    ) {
        DictionaryTopAppBar(
            selectedDestination,
            onClick = { route, i ->
                if (i != selectedDestination) {
                    navController.navigate(route)
                    selectedDestination = i
                }
            }
        )
        NavHost(
            navController = navController,
            startDestination = startDestination.route
        ) {
            TabDestinations.entries.forEach { d ->
                composable(d.route) {
                    when (d) {
                        TabDestinations.Bangla -> Banglabody(
                            onSearch = onSearch
                        )

                        TabDestinations.English -> Englishbody(
                            onSearch = onSearch
                        )
                    }
                }
            }
        }
    }
}


