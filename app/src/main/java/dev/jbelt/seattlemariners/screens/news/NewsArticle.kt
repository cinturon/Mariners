package dev.jbelt.seattlemariners.screens.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.jbelt.seattlemariners.viewmodel.ArticleViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val TAG = "NewsArticle"

@Composable
fun NewsArticle(articleId: Long, onBackClick: () -> Unit) {
    val viewModel: ArticleViewModel = hiltViewModel()
    val articleResponse = viewModel.article.collectAsState().value

    // Fetch article when composable is first displayed
    LaunchedEffect(articleId) {
        Log.d(TAG, "Fetching article with ID: $articleId")
        viewModel.fetchArticle(articleId)
    }

    // Clear video player and resources when leaving the screen
    DisposableEffect(Unit) {
        onDispose {
            Log.d(TAG, "Clearing article resources and stopping video playback")
            // Video player will be automatically released by VideoPlayer's DisposableEffect
            // This ensures the screen is cleaned up when user navigates away
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.85f))
            .verticalScroll(rememberScrollState())
    ) {
        // Back button header
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        // Display article if loaded
        if (articleResponse != null && articleResponse.headlines?.isNotEmpty() == true) {
            val article = articleResponse.headlines.first()

            // Article headline
            article.headline?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            // Video player (if video is available)
            article.video?.firstOrNull()?.let { video ->
                Log.d(TAG, "Video object found: $video")
                Log.d(TAG, "Video links: ${video.links}")
                Log.d(TAG, "Video source: ${video.links?.source}")


                // Extract video URL
                val videoUrl = video.links?.source?.hls?.href

                Log.d(TAG, "Video URL extracted: $videoUrl")

                videoUrl?.let { url ->
                    if (url.isNotBlank()) {
                        Log.d(TAG, "Loading video player with URL: $url")
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            VideoPlayer(
                                videoUrl = url,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(220.dp)
                            )
                        }
                    } else {
                        Log.d(TAG, "Video URL is blank")
                    }
                }
            }

            // Featured image (only show if no video)
            if (article.video == null) {
                article.images?.firstOrNull()?.let { image ->
                    AsyncImage(
                        model = image.url,
                        contentDescription = "Featured image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            // Published date
            article.published?.let {
                Text(
                    text = formatDate(it),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    fontWeight = FontWeight.ExtraLight
                )
            }

            // Description
            article.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    fontWeight = FontWeight.Light
                )
            }

            // Story
            article.story?.let { htmlStory ->
                val plainText = HtmlCompat.fromHtml(
                    htmlStory,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                ).toString()

                Text(
                    text = plainText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        } else {
            // Loading state
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(color = Color.White)
                    Text(
                        text = "Loading article...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 16.dp),
                        textAlign = TextAlign.Center
                    )
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
