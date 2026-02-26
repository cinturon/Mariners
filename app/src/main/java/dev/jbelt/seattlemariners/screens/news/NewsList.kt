package dev.jbelt.seattlemariners.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.jbelt.seattlemariners.models.Article
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun NewsList(articles: List<Article>, onArticleClick: (Long) -> Unit = {}) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(articles) { article ->
            val articleId = article.id ?: 0L
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { onArticleClick(articleId) },
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                // Image Card on top
                article.images?.firstOrNull()?.let { image ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        border = CardDefaults.outlinedCardBorder(
                            true
                        ),
                        elevation = CardDefaults.cardElevation(8.dp),
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
                    ) {
                        Box {
                            AsyncImage(
                                model = image.url,
                                contentDescription = article.headline,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentScale = ContentScale.Crop
                            )

                            // Show play icon if article has video
                            if (article.video?.isNotEmpty() == true) {
                                Icon(
                                    imageVector = Icons.Filled.PlayCircle,
                                    contentDescription = "Video available",
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .size(64.dp),
                                    tint = Color.White.copy(alpha = 0.9f)
                                )
                            }
                        }
                    }
                }

                // Info Card on the bottom
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A1A1A)
                    ),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(
                        topStart = if (article.images?.firstOrNull() != null) 0.dp else 8.dp,
                        topEnd = if (article.images?.firstOrNull() != null) 0.dp else 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        article.headline?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 3,
                                color = Color.White
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp),
                            thickness = 1.dp,
                            color = Color.White
                        )
                        article.description?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(top = 4.dp),
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 5,
                                color = Color(0xFFCCCCCC)
                            )
                        }

                        Text(
                            text = formatDate(article.published.toString()),
                            modifier = Modifier.padding(top = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF999999)
                        )
                    }
                }
            }
        }
    }
}

private fun formatDate(isoDate: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(isoDate)
        val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm a", Locale.ENGLISH)
        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        isoDate // Return original if parsing fails
    }
}
