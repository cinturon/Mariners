package dev.jbelt.seattlemariners.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jbelt.seattlemariners.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val newsResponse = viewModel.news.collectAsState().value
    val selectedArticleId = remember { mutableStateOf<Long?>(null) }

    if (selectedArticleId.value != null) {
        // Show article detail screen - fetch from API
        NewsArticle(
            articleId = selectedArticleId.value!!,
            onBackClick = { selectedArticleId.value = null }
        )
    } else {
        // Show news list
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "MLB News",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            NewsList(
                articles = newsResponse?.articles ?: emptyList(),
                onArticleClick = { articleId -> selectedArticleId.value = articleId }
            )
        }
    }

}