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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.capx.dictionary.R
import com.capx.dictionary.ui.screens.home.components.HomeBody


enum class Destinations(
    val route:String,
    val label: String,
    val image: Int,
    val contentDescription:String
) {
    Home("home", "Home", R.drawable.home, "Home Icon"),
    Words("dictionary", "Dictionary", R.drawable.list, "list of all words"),
    Bookmarks("bookmark", "Bookmark", R.drawable.bookmark, "bookmark icon"),
}

@Composable
fun HomeScreen(onSearch: (text: String) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar() {
                NavigationBar() {
                    Destinations.entries.forEach {
                        NavigationBarItem(
                            selected = it.route=="home",
                            label = {
                                Text(it.label)
                            },
                            icon = {
                                Icon(painter = painterResource(it.image), it.contentDescription)
                            },
                            onClick = {

                            }
                        )
                    }

                }

            }
        }
    ) { innerPadding ->
        HomeBody(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onSearch = onSearch
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onSearch = {})
}
