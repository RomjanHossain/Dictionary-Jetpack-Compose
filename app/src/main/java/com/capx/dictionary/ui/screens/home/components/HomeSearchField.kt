package com.capx.dictionary.ui.screens.home.components

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import com.capx.dictionary.R

@Composable
fun HomeSearchField(modifier: Modifier = Modifier, onSearch: (text: String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(text)
            keyboardController?.hide()
        }),
        maxLines = 1,
//        keyboardActions = KeyboardActions.Default.onSearch,
        prefix = {
            Icon(painter = painterResource(R.drawable.search), "Search Icon")
        }
    )
}
