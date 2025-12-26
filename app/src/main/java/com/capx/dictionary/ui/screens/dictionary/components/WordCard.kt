package com.capx.dictionary.ui.screens.dictionary.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WordCard(
    title:String,
    onSearch:(title:String)->Unit,
){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(enabled = true, onClick = {
                onSearch(title)
            }),
        shape = ShapeDefaults.Small
    ) {
        Text(
            title,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
        )
    }
}