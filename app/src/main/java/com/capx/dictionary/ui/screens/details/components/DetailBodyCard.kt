package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.AppLogger
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun DetailCardForTransList(
    modifier: Modifier = Modifier,
    title: String,
    content: List<DictionaryDataDetails>
) {
    AppLogger.info("Content ($title) Size: ${content.size} || ${content}")
    if (content.size > 1) {
        Card(modifier = modifier, shape = RoundedCornerShape(10.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                Icon(painterResource(R.drawable.translate), "Translate")
                Spacer(Modifier.width(5.dp))
                Text(
                    "${title.uppercase()} DEFINATIONS & MEANINGS",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            HorizontalDivider()
            Column(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 5.dp)
            ) {
                content.forEachIndexed { i, curr ->

                    Column() {
                        Row(
                            modifier = Modifier.padding(vertical = 10.dp),
                            verticalAlignment = Alignment.Top,
//                        horizontalArrangement = Arrangement.Center,
                        ) {
                            CircularAvater("${i + 1}")
                            Spacer(Modifier.width(10.dp))
                            Text(
                                curr.body ?: "",
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface)
                            )
                        }
                        HorizontalDivider(thickness = 0.2.dp)
                    }
                }
            }
        }
    } else if (content.size == 1) {
        val content = content.first().body
        DetailCardForTrans(modifier = modifier, title = title, content ?: "")
    }
}

@Composable
fun CircularAvater(title: String) {
    Card(
        shape = CircleShape,
        modifier = Modifier
            .padding(top = 4.dp)
            .size(20.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = PrimaryColor.copy(alpha = 0.2f))
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(title, color = PrimaryColor, style = MaterialTheme.typography.labelSmall)
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
                painterResource(R.drawable.baseline_menu_book_24),
                "app Icon",
                tint = PrimaryColor,
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 10.dp)
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
                    body = "Python is the largest shit in the entire world. now its the biggest ",
                    originalFile = "b2b"
                ),
                DictionaryDataDetails(
                    id = 2,
                    title = "nice",
                    body = "Python is the largest shit in the entire world. now its the biggest ",
                    originalFile = "b2b"
                ),
                DictionaryDataDetails(
                    id = 1,
                    title = "nice",
                    body = "Python is the largest shit in the entire world. now its the biggest ",
                    originalFile = "b2b"
                )
            )
        )
    }
}
