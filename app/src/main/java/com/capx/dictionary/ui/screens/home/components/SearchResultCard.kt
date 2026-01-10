package com.capx.dictionary.ui.screens.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.capx.dictionary.R
import com.capx.dictionary.data.entity.DictionaryFts
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.ui.theme.PrimaryColor
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun SearchResultCard(
    curr: DictionaryFts?,
    query: String,
    onSearch: (String, Int) -> Unit,
) {

    Card(
        modifier = Modifier
            .clickable(
                enabled = true,
                onClick = {
                    if (curr?.title != null) {
                        onSearch(curr.title, curr.id ?: -1)
                    }
                }),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularIcon(des = "Search", R.drawable.search)
                Spacer(Modifier.width(10.dp))
                Text(
//                    curr?.title ?: "",
                    annotatedHighlightedString(curr?.title ?: "", query = query),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
            Icon(painterResource(R.drawable.go_arrow), "Go Arrow")
        }
    }
}

@Composable
fun annotatedHighlightedString(
    text: String,
    query: String,
    highlightStyle: SpanStyle = SpanStyle(
        fontWeight = FontWeight.Bold,
        color = PrimaryColor
    )
): AnnotatedString {
    return buildAnnotatedString {
        val start = 0
        // Case-insensitive search
        val indexOfQuery = text.indexOf(query, ignoreCase = true)

        if (query.isNotEmpty() && indexOfQuery != -1) {
            append(text.take(indexOfQuery))

            withStyle(style = highlightStyle) {
                append(text.substring(indexOfQuery, indexOfQuery + query.length))
            }

            append(text.substring(indexOfQuery + query.length))
        } else {
            append(text)
        }
    }
}

@Composable
fun CircularIcon(
    des: String,
    @DrawableRes id: Int
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(top = 4.dp)
            .size(40.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = PrimaryColor.copy(alpha = 0.2f))
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(painter = painterResource(id), des, tint = PrimaryColor)
        }
    }
}

@ThemePreviews
@Composable
fun SearchResultCardPreview() {
    DictionaryTheme() {
        SearchResultCard(
            curr = DictionaryFts(
                id = 2,
                title = "NICE",
                source_lang = "e"
            ),
            query = "NI"
        ) { a, b -> }
    }

}