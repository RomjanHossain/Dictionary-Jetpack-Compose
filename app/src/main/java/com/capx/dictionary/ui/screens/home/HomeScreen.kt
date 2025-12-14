package com.capx.dictionary.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.ui.theme.DictionaryTheme


@Composable
fun HomeScreen(onSearch:(text:String)->Unit){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeBody(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        onSearch=onSearch
                    )
                }
}


@Composable
fun HomeBody( modifier: Modifier = Modifier, onSearch:(text:String)->Unit) {
    Column(
    modifier = modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.app_name), style = MaterialTheme.typography.displayLarge)
        HomeSearchField(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
            onSearch = onSearch
        )
        RecentSearchedWords()
    }
}

@Composable
fun HomeSearchField(modifier: Modifier= Modifier, onSearch:(text: String)->Unit){
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text=it
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search, keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(text)
            keyboardController?.hide()
        }),
        maxLines = 1,
//        keyboardActions = KeyboardActions.Default.onSearch,
        prefix = {
            Icon(painter = painterResource(R.drawable.search),"Search Icon")
        }
    )
}
@Composable
fun RecentSearchedWords(){
    // title
    Text(stringResource(R.string.recent), style = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.Bold
    ))
    Spacer(Modifier.height(10.dp))
    // contains
    Text(stringResource(R.string.recent))
    Text(stringResource(R.string.recent))
    Text(stringResource(R.string.recent))
    Text(stringResource(R.string.recent))

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onSearch = {})
}
