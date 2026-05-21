package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.sp
import com.capx.dictionary.R
import com.capx.dictionary.data.entity.DictionaryDataDetails
import com.capx.dictionary.ui.components.GlassCard
import com.capx.dictionary.ui.theme.DictionaryTheme
import com.capx.dictionary.utils.ThemePreviews

@Composable
fun DetailCardForTransList(
    modifier: Modifier = Modifier,
    title: String,
    content: List<DictionaryDataDetails>
) {
    if (content.isNotEmpty()) {
        Column(modifier = modifier) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    painterResource(R.drawable.translate),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "${title.uppercase()} MEANINGS",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 0.1.sp
                    )
                )
            }

            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    content.forEachIndexed { i, curr ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            CircularAvater("${i + 1}")
                            Text(
                                text = curr.body ?: "",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (i < content.size - 1) {
                            HorizontalDivider(
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                thickness = 0.5.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CircularAvater(title: String) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                CircleShape
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun DetailCardForTrans(modifier: Modifier = Modifier, title: String, content: String) {
    DetailCardForTransList(
        modifier = modifier,
        title = title,
        content = listOf(
            DictionaryDataDetails(
                id = null,
                title = null,
                body = content,
                originalFile = null
            )
        )
    )
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
