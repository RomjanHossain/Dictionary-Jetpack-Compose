package com.capx.dictionary.ui.screens.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(value:String, onGoBack:()->Unit){
    Scaffold(modifier = Modifier.fillMaxSize(),
topBar = {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onGoBack) {
                Icon(painter = painterResource(R.drawable.nav_back), "Navigation back icon")
            }
        },
        title = {
            Text(value)
        },

    )
}
        ) { innerPadding ->
        DetailBody(
            modifier = Modifier.padding(innerPadding).fillMaxSize().padding(horizontal = 20.dp),
            value = value
        )
    }
}

@Composable
fun DetailBody(value:String, modifier: Modifier = Modifier){
    Text(value, modifier = modifier, style = MaterialTheme.typography.displaySmall)
}

@Composable
fun DetailCardForTrans(modifier: Modifier= Modifier){
    Card(modifier = modifier) {
        Text(stringResource(R.string.Bengali))
        Divider()
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewDetailCard(){
    DetailCardForTrans()
}



@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen(){
    DetailScreen(value = "Hello", onGoBack = {})
}