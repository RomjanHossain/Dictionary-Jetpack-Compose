package com.capx.dictionary.ui.screens.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DictionarySearchFields(
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    text: String,
    onSearch: (String) -> Unit,
    isTrailingIcon: Boolean,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp),
        value = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(20.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(text)
            }
        ),
        maxLines = 1,
        prefix = {
            Icon(painter = painterResource(R.drawable.search), "Search Icon")
        },
        placeholder = {
            Text("Search English or Bangla Words")
        },
        trailingIcon = {
            if (isTrailingIcon) {
                CircularWavyProgressIndicator(modifier = Modifier.padding(5.dp))
            }
        }
    )
}