package com.capx.dictionary.ui.screens.dictionary

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.capx.dictionary.ui.screens.dictionary.viewmodel.DictionaryViewmodel

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
    modifier: Modifier = Modifier, onSearch: (text: String) -> Unit
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
                        TabDestinations.B2B -> B2Bbody(onSearch = onSearch)
                        TabDestinations.B2E -> B2Ebody(onSearch = onSearch)
                        TabDestinations.E2E -> E2Ebody(onSearch = onSearch)
                        TabDestinations.E2B -> E2Bbody(onSearch = onSearch)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun B2Ebody(
    viewmodel: DictionaryViewmodel = hiltViewModel(),
    onSearch: (text: String) -> Unit
) {
    val data = viewmodel.b2e.collectAsLazyPagingItems()
    LazyColumn() {
        items(data.itemCount) {
            val curr = data[it]
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable(enabled = true, onClick = {
                        onSearch(curr?.title ?: "")
                    }),
                shape = ShapeDefaults.Small
            ) {
                Text(
                    curr?.title ?: "",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                )
            }
        }
        if (data.loadState.refresh == LoadState.Loading) {
            item {
                CircularWavyProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun E2Bbody(
    viewmodel: DictionaryViewmodel = hiltViewModel(),
    onSearch: (text: String) -> Unit
) {
    val data = viewmodel.e2b.collectAsLazyPagingItems()
    LazyColumn() {
        items(data.itemCount) {
            val curr = data[it]
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable(
                        enabled = true,
                        onClick = {
                            onSearch(curr?.title ?: "")
                        }),
                shape = ShapeDefaults.Small
            ) {
                Text(
                    curr?.title ?: "",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                )
            }
        }
        if (data.loadState.refresh == LoadState.Loading) {
            item {
                CircularWavyProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun E2Ebody(
    viewmodel: DictionaryViewmodel = hiltViewModel(),
    onSearch: (text: String) -> Unit
) {
    val data = viewmodel.e2e.collectAsLazyPagingItems()
    LazyColumn() {
        items(data.itemCount) {
            val curr = data[it]
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable(
                        enabled = true,
                        onClick = {
                            onSearch(curr?.title ?: "")
                        }),
                shape = ShapeDefaults.Small
            ) {
                Text(
                    curr?.title ?: "",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                )
            }
        }
        if (data.loadState.refresh == LoadState.Loading) {
            item {
                CircularWavyProgressIndicator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun B2Bbody(
    viewmodel: DictionaryViewmodel = hiltViewModel(),
    onSearch: (text: String) -> Unit
) {
    val data = viewmodel.b2b.collectAsLazyPagingItems()
    LazyColumn() {
        items(data.itemCount) {
            val curr = data[it]
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .clickable(
                        enabled = true,
                        onClick = {
                            onSearch(curr?.title ?: "")
                        }),
                shape = ShapeDefaults.Small
            ) {
                Text(
                    curr?.title ?: "",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
                )
            }
        }
        if (data.loadState.refresh == LoadState.Loading) {
            item {
                CircularWavyProgressIndicator()
            }
        }
    }
}
