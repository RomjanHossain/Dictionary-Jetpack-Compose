package com.capx.dictionary.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.R
import com.capx.dictionary.ui.screens.home.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeSearchField(
    modifier: Modifier = Modifier,
    onSearch: (text: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
//    var text by remember { mutableStateOf("") }
    val text = viewModel.searchInput.collectAsState().value
    val state = viewModel.homeState.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current
    Column() {

        OutlinedTextField(
            modifier = modifier.padding(end = 10.dp),
            value = text,
            onValueChange = viewModel::onSearchText,
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
            },
            trailingIcon = {
                if (state.isLoading) {
                    CircularWavyProgressIndicator()
                }

            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card() {
            Column() {
                if (state.searchedValues.isNotEmpty()) {

                    state.searchedValues.forEach {
                        Text(it.title ?: "")
                        Divider()
                    }
                } else {
                    Text("NOthing Found")
                }
            }
        }
    }
}
