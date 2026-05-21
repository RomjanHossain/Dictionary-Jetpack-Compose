package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capx.dictionary.ui.screens.dictionary.TabDestinations
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.GlassBorder
import com.capx.dictionary.ui.theme.GlassSurface
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun DictionaryTopAppBar(
    selectedDestination: Int,
    onClick: (String, Int) -> Unit
) {
    val items = TabDestinations.entries
    val cornerRadius = 24.dp

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .background(GlassSurface)
            .border(1.dp, GlassBorder, RoundedCornerShape(cornerRadius))
            .padding(4.dp)
    ) {
        val maxWidth = maxWidth
        val tabWidth = maxWidth / items.size

        val indicatorOffset by animateDpAsState(
            targetValue = tabWidth * selectedDestination,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMediumLow
            ),
            label = "indicator"
        )

        Box(
            modifier = Modifier
                .offset(x = indicatorOffset)
                .width(tabWidth)
                .fillMaxHeight()
                .clip(RoundedCornerShape(cornerRadius - 4.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                            MaterialTheme.colorScheme.primary
                        )
                    )
                )
        )

        Row(modifier = Modifier.fillMaxSize()) {
            items.forEachIndexed { i, d ->
                val isSelected = selectedDestination == i
                val textColor by animateColorAsState(
                    targetValue = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                    label = "textColor"
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(cornerRadius - 4.dp))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onClick(d.route, i) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = d.label,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            color = textColor
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
    DictionaryTheme {
        DictionaryTopAppBar(0, onClick = { _, _ -> })
    }
}

@Composable
@ThemePreviews
fun DictionaryTopAppBarEnglishPreview() {
    DictionaryTheme {
        DictionaryTopAppBar(1, onClick = { _, _ -> })
    }
}
