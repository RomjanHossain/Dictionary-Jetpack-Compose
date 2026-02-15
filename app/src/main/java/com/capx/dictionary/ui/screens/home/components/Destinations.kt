package com.capx.dictionary.ui.screens.home.components

import com.capx.dictionary.R

enum class Destinations(
    val route: String,
    val label: String,
    val image: Int,
    val contentDescription: String
) {
    Home("home", "Home", R.drawable.homefill, "Home Icon"),
    Words("dictionary", "Browse", R.drawable.baseline_menu_book_24, "list of all words"),
    Bookmarks("bookmark", "Saved", R.drawable.favorite, "bookmark icon"),
}
