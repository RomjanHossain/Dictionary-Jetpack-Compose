package com.capx.dictionary.ui.screens.Splash.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capx.dictionary.R
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.GlassBorder
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun AppIcon() {
    Box(
        modifier = Modifier
            .size(160.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
                    )
                )
            )
            .border(1.dp, GlassBorder, RoundedCornerShape(40.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Aa",
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        Icon(
            painter = painterResource(R.drawable.baseline_menu_book_24),
            contentDescription = "App Logo",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(80.dp)
        )

        Text(
            text = "অ",
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@ThemePreviews
@Composable
fun PreviewIcon() {
    DictionaryTheme() {
        AppIcon()
    }
}