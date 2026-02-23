package dev.jbelt.seattlemariners.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jbelt.seattlemariners.screens.events.EventCard
import dev.jbelt.seattlemariners.screens.news.NewsCard
import dev.jbelt.seattlemariners.viewmodel.EventsViewModel
import dev.jbelt.seattlemariners.viewmodel.NewsViewModel
import dev.jbelt.seattlemariners.viewmodel.StatsViewModel

@Composable
fun HomeScreen(
    statsViewModel: StatsViewModel = hiltViewModel(),
    eventsViewModel: EventsViewModel = hiltViewModel(),
    newsViewModel: NewsViewModel = hiltViewModel(),
    onArticleClick: (Long) -> Unit = {}
) {
    val game = statsViewModel.todaysGame.collectAsState().value
    val isLoading = statsViewModel.isLoading.collectAsState().value

    val news = newsViewModel.news.collectAsState().value
    val latestStory = news?.articles?.take(2) ?: emptyList()

    LaunchedEffect(Unit) {
        eventsViewModel.fetchUpcomingEvents()
    }

    val upcomingGames = eventsViewModel.eventsList.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Top Bar with Title and Refresh Button
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(Color.Transparent),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Today's Game",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                IconButton(
                    onClick = { statsViewModel.refreshGame() },
                    enabled = !isLoading,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(8.dp),
                            color = Color(0xFF00C8C8),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Refresh",
                            tint = Color(0xFF00C8C8)
                        )
                    }
                }
            }
        }

        // GameCard
        item {
            game?.let {
                GameCard(game = it)
            }
        }

        // Upcoming Games Section Title
        item {
            Card(
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(Color(0xFF1A1A1A).copy(alpha = 0.8f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Upcoming Games",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Upcoming Games List
        items(upcomingGames) { event ->
            Box(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                EventCard(event)
            }
        }

        // Latest News Section Title
        item {
            Card(
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(Color(0xFF1A1A1A).copy(alpha = 0.8f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "Latest News",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Latest News List
        items(latestStory) { article ->
            NewsCard(
                article = article,
                onArticleClick = onArticleClick
            )
        }
    }
}

