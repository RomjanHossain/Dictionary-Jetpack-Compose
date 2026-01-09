package com.capx.dictionary.ui.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.capx.dictionary.R
import com.capx.dictionary.ui.screens.details.viewmodels.DetailScreenState
import com.capx.dictionary.ui.screens.details.viewmodels.DetailViewModel
import com.capx.dictionary.ui.screens.dictionary.components.CenterLoading
import com.capx.dictionary.utils.AppLogger


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun DetailBody(
    value: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    viewModel.searchForTheWord(value)
    val state = viewModel.detailState.collectAsState()
    Column(modifier = modifier) {
        Text(
            value,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        Spacer(Modifier.height(20.dp))
        when (state.value) {
            is DetailScreenState.Error -> Text((state.value as DetailScreenState.Error).msg)
            DetailScreenState.Loading -> CenterLoading(
                modifier = Modifier.fillMaxSize(),
            )

            is DetailScreenState.Success -> {
                val data = (state.value as DetailScreenState.Success).data
                val banglaMeaning = data.filter {
                    it.originalFile?.contains("b2b") ?: false || it.originalFile?.contains(
                        "e2b"
                    ) ?: false
                }

                val englishMeaning = data.filter {
                    it.originalFile?.contains("b2e") ?: false || it.originalFile?.contains(
                        "e2e"
                    ) ?: false
                }

                LazyColumn() {
                    items(data.size) {
                        val curr = data[it]
                        AppLogger.debug("Current: ${curr.title} - ${curr.originalFile}")
                        val title =
                            if ((curr.originalFile?.contains("b2b") ?: false || curr.originalFile?.contains(
                                    "e2b"
                                ) ?: false)
                            ) stringResource(R.string.Bengali) else stringResource(R.string.English)
                        DetailCardForTrans(
                            title = title,
                            content = curr.body ?: "",
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailBodyPreview() {
    DetailBody(value = "Test")
}

