package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun DetailCardForTransList(
    modifier: Modifier = Modifier,
    title: String,
    content: List<DictionaryDataDetails>
) {
    if (content.size > 1) {
        Card(modifier = modifier, shape = RoundedCornerShape(10.dp)) {
            Row() {
                Icon(painterResource(R.drawable.translate), "Translate")
                Text("${title.uppercase()} DEFINATIONS & MEANINGS")
            }
            HorizontalDivider()
            LazyColumn() {
                items(content.size) {
                    val curr = content[it]
                    Row() {
                        CircularAvater("$it")
                        Text(curr.body ?: "")
                    }
                }
            }
        }
    } else {
        val content = content.first().body
        DetailCardForTrans(modifier = modifier, title = title, content ?: "")
    }
}

@Composable
fun CircularAvater(title: String) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .size(40.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = PrimaryColor.copy(alpha = 0.2f))
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(title, color = PrimaryColor)
        }
    }
}

@Composable
fun DetailCardForTrans(modifier: Modifier = Modifier, title: String, content: String) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painterResource(R.drawable.ic_launcher_foreground),
                "app Icon",
                tint = PrimaryColor,
                modifier = Modifier.size(30.dp)
            )
            Text(
                "$title Meaning",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
//                modifier = Modifier.padding(5.dp)
            )
        }
        Card(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {
            Text(content, modifier = Modifier.padding(15.dp))
        }
    }
}

@ThemePreviews
@Composable
fun DetailCardPreview() {
    DictionaryTheme() {
        DetailCardForTrans(
            title = "Bangla",
            content = "THis is content for asdf asdkf"
        )
    }
}

@ThemePreviews
@Composable
fun DetailCardListPreview() {
    DictionaryTheme() {
        DetailCardForTransList(
            title = "Bangla",
            content = listOf<DictionaryDataDetails>(
                DictionaryDataDetails(
                    id = 2,
                    title = "nice",
                    body = "python",
                    originalFile = "b2b"
                ),
                DictionaryDataDetails(
                    id = 2,
                    title = "nice",
                    body = "python",
                    originalFile = "b2b"
                ),
                DictionaryDataDetails(
                    id = 1,
                    title = "nice",
                    body = "python",
                    originalFile = "b2b"
                )
            )
        )
    }
}
