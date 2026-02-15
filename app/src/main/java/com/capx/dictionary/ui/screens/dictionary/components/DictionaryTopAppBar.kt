package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capx.dictionary.ui.screens.dictionary.TabDestinations
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.ui.theme.TabBackgroundDark
import com.capx.dictionary.ui.theme.TabBackgroundLight
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun DictionaryTopAppBar(
    selectedDestination: Int,
    onClick: (String, Int) -> Unit
) {
    val items = TabDestinations.entries
    val cornerRadius = 8.dp

    val color = if (isSystemInDarkTheme()) TabBackgroundDark else TabBackgroundLight
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .background(color) // iOS light gray background
            .padding(5.dp) // Gap between edge and indicator
    ) {
        val maxWidth = maxWidth
        val tabWidth = maxWidth / items.size

        // The Sliding Indicator (The "Thumb")
        val indicatorOffset by animateDpAsState(
            targetValue = tabWidth * selectedDestination,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            label = "indicator"
        )

        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(tabWidth)
                .fillMaxHeight()
                .shadow(1.dp, RoundedCornerShape(cornerRadius - 2.dp))
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    RoundedCornerShape(cornerRadius - 2.dp)
                )
        )

        // The Labels
        Row(modifier = Modifier.fillMaxSize()) {
            items.forEachIndexed { i, d ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null // Removes the Material ripple
                        ) { onClick(d.route, i) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = d.label,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = if (i == selectedDestination) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (selectedDestination == i) PrimaryColor else MaterialTheme.colorScheme.outline
                        )
                    )
                }
            }
        }
    }
}

@Composable
@ThemePreviews
fun DictionaryTopAppBarPreview() {
    DictionaryTheme() {
        DictionaryTopAppBar(1, onClick = { a, b -> })
    }
}
