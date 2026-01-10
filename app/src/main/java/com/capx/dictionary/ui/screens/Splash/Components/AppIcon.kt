package com.capx.dictionary.ui.screens.Splash.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun AppIcon(){
    Box(
        modifier = Modifier
            .background(PrimaryColor.copy(alpha = 0.2f), shape = RoundedCornerShape(28.dp))
    ) {
        Text(
            "Aa",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        )
        Icon(
            painter = painterResource(R.drawable.baseline_menu_book_24),
            "App icon",
            tint = PrimaryColor,
            modifier = Modifier
                .padding(30.dp)
                .size(100.dp)
        )
        Text(
            "অ",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}

@ThemePreviews
@Composable
fun PreviewIcon(){
    DictionaryTheme() {
        AppIcon()
    }
}