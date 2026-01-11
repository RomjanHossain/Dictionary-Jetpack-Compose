package com.capx.dictionary.ui.screens.home.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.NavigationUnselectedColor
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun BottomNavigationBar(
    selectedDestination: String?,
    onSelect: (Int, String) -> Unit,
) {
    NavigationBar() {
        Destinations.entries.forEachIndexed { i, d ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryColor,
                    selectedTextColor = PrimaryColor,
                    unselectedIconColor = NavigationUnselectedColor,
                    unselectedTextColor = NavigationUnselectedColor,
                    indicatorColor = PrimaryColor.copy(alpha = 0.2f)
                ),
                selected = d.route == selectedDestination,
                label = {
                    Text(d.label)
                },
                icon = {
                    Icon(painter = painterResource(d.image), d.contentDescription)
                },

                onClick = {
                    onSelect(i, d.route)
                }
            )
        }
    }
}

@ThemePreviews
@Composable
fun BottomNavigationPreview() {
    DictionaryTheme() {
        BottomNavigationBar(
            "home",
            onSelect = { a, b ->
            },
        )
    }
}

