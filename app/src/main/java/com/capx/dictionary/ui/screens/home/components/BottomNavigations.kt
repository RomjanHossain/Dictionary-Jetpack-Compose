package com.capx.dictionary.ui.screens.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capx.dictionary.ui.components.GlassCard
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun BottomNavigationBar(
    selectedDestination: String?,
    onSelect: (Int, String) -> Unit,
) {
    GlassCard(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Destinations.entries.forEachIndexed { i, d ->
                val isSelected = d.route == selectedDestination
                
                BottomNavItem(
                    destination = d,
                    isSelected = isSelected,
                    onClick = { onSelect(i, d.route) }
                )
            }
        }
    }
}

@Composable
fun BottomNavItem(
    destination: Destinations,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(if (isSelected) 1.1f else 1f, label = "scale")
    val iconColor by animateColorAsState(
        if (isSelected) MaterialTheme.colorScheme.primary 
        else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        label = "color"
    )

    Column(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(destination.image),
            contentDescription = destination.contentDescription,
            modifier = Modifier.size(24.dp),
            tint = iconColor
        )
        Text(
            text = destination.label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                color = iconColor
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
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

