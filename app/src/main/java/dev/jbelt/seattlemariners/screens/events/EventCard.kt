package dev.jbelt.seattlemariners.screens.events

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import dev.jbelt.seattlemariners.models.EventsResponse

@Composable
fun EventCard(event: EventsResponse) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                event.url?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                    context.startActivity(intent)
                }
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        ),
        border = CardDefaults.outlinedCardBorder(
            true
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left Side - Event Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Event Name
                event.name?.let { name ->
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                }

                // Event Date
                event.dates?.start?.localDate?.let { date ->
                    Text(
                        text = "📅 $date",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF00C8C8)
                    )
                }

                // Venue
                event.embedded?.venues?.firstOrNull()?.let { venue ->
                    venue.name?.let { venueName ->
                        Text(
                            text = venueName,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.LightGray,
                            maxLines = 1
                        )
                    }
                }

                // Price Range
                event.priceRanges?.firstOrNull()?.let { priceRange ->
                    val priceText = buildString {
                        priceRange.min?.let { append("$${it}") }
                        if (priceRange.min != null && priceRange.max != null) {
                            append(" - ")
                        }
                        priceRange.max?.let { append("$${it}") }
                    }
                    Text(
                        text = priceText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF4CAF50)
                    )
                }
            }

            // Right Side - Team Logos
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                // First team image
                AsyncImage(
                    model = event.embedded?.attractions?.firstOrNull()?.images?.firstOrNull()?.url,
                    contentDescription = "Team Image",
                    modifier = Modifier
                        .size(60.dp, 40.dp)
                        .background(Color(0xFF333333), RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.FillBounds
                )

                Text(
                    text = "vs",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

                // Second team image
                if ((event.embedded?.attractions?.size ?: 0) > 1) {
                    AsyncImage(
                        model = event.embedded?.attractions?.get(1)?.images?.firstOrNull()?.url,
                        contentDescription = "Team Image",
                        modifier = Modifier
                            .size(60.dp, 40.dp)
                            .background(Color(0xFF333333), RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}
