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

enum class TabDestinations(
    val route: String,
    val label: String,
) {
    B2B("b2b", "B2B"),
    B2E("b2c", "B2E"),
    E2E("e2e", "E2E"),
    E2B("e2b", "E2B"),
}

@Composable
fun DictionaryScreen(
    modifier: Modifier
) {
    val navController = rememberNavController()
    val startDestination = TabDestinations.B2B
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Column(
        modifier = modifier,
    ) {
        PrimaryTabRow(
            selectedTabIndex = selectedDestination,
        ) {
            TabDestinations.entries.forEachIndexed { i, d ->
                Tab(
                    selected = i == selectedDestination,
                    onClick = {
                        if (i != selectedDestination) {
                            navController.navigate(d.route)
                            selectedDestination = i
                        }
                    },
                    text = {
                        Text(d.label)
                    }
                )
            }
        }
        NavHost(
            navController = navController,
            startDestination = startDestination.route
        ) {
            TabDestinations.entries.forEach { d ->
                composable(d.route) {
                    when (d) {
                        TabDestinations.B2B -> B2Bbody()
                        TabDestinations.B2E -> B2Ebody()
                        TabDestinations.E2E -> E2Ebody()
                        TabDestinations.E2B -> E2Bbody()
                    }
                }
            }
        }
    }
}

@Composable
fun B2Ebody() {
    Text("b2e")
}

@Composable
fun E2Bbody() {
    Text("e2b")
}

@Composable
fun E2Ebody() {
    Text("e2e")
}

@Composable
fun B2Bbody() {
    Text("b2b")
}