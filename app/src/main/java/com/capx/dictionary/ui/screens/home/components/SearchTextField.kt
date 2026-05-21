package com.capx.dictionary.ui.screens.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.ui.components.GlassTextField


@Composable
fun DictionarySearchFields(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    text: String,
    onSearch: (String) -> Unit,
    isTrailingIcon: Boolean,
) {
    GlassTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        placeholder = "Search English or Bangla",
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = "Search Icon",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingIcon = {
            if (isTrailingIcon) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}