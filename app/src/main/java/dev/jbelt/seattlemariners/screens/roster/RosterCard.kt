package dev.jbelt.seattlemariners.screens.roster

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import dev.jbelt.seattlemariners.models.Athlete

@Composable
fun RosterCard(athlete: Athlete) {
    val isShowingStats = remember { mutableStateOf(false) }

    // Roster Card (Front)
    Card(
        elevation = CardDefaults.cardElevation(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { isShowingStats.value = true },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF005C5C),  // Dark teal/navy blue (Mariners primary)
                            Color(0xFF0A3A3A),  // Darker teal
                            Color(0xFFC4CED4)   // Light silver (Mariners secondary)
                        )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = athlete.headshot?.href,
                contentDescription = athlete.displayName,
            )
            Text(
                text = athlete.displayName,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }

    // Fullscreen StatCard Dialog
    if (isShowingStats.value) {
        Dialog(
            onDismissRequest = { isShowingStats.value = false },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            )
        ) {
            AnimatedVisibility(
                visible = isShowingStats.value,
                enter = fadeIn(animationSpec = tween(300)) + scaleIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300)) + scaleOut(animationSpec = tween(300))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable { isShowingStats.value = false },
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(24.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .padding(16.dp)
                            .clickable(enabled = false) { },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Box {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                // Header section with team colors
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF005C5C))
                                        .padding(16.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "SEATTLE MARINERS",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.White,
                                            textAlign = TextAlign.Center
                                        )
                                        athlete.jersey?.let {
                                            Text(
                                                text = "#$it",
                                                style = MaterialTheme.typography.headlineLarge,
                                                color = Color(0xFFC4CED4),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }

                                // Player photo section
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFFF5F5F5))
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = athlete.headshot?.href,
                                        contentDescription = athlete.displayName,
                                        modifier = Modifier.fillMaxWidth(0.7f)
                                    )
                                }

                                // Player name section
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF005C5C))
                                        .padding(12.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = athlete.fullName.uppercase(),
                                            style = MaterialTheme.typography.titleLarge,
                                            color = Color.White,
                                            textAlign = TextAlign.Center
                                        )
                                        athlete.position?.displayName?.let {
                                            Text(
                                                text = it.uppercase(),
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = Color(0xFFC4CED4),
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    }
                                }

                                // Stats section with border
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(16.dp)
                                ) {
                                    // Bio info
                                    athlete.age?.let {
                                        StatRow("AGE", it.toString())
                                    }

                                    athlete.bats?.displayValue?.let { bats ->
                                        athlete.throws?.displayValue?.let { throws ->
                                            StatRow("B/T", "$bats / $throws")
                                        }
                                    }

                                    athlete.displayHeight?.let { height ->
                                        athlete.displayWeight?.let { weight ->
                                            StatRow("HT/WT", "$height / $weight")
                                        }
                                    }

                                    athlete.birthPlace?.displayText?.let {
                                        StatRow("BIRTHPLACE", it)
                                    }

                                    athlete.debutYear?.let {
                                        StatRow("MLB DEBUT", it.toString())
                                    }

                                    athlete.college?.name?.let {
                                        StatRow("COLLEGE", it)
                                    }

                                    athlete.experience?.years?.let {
                                        StatRow("EXPERIENCE", "$it years")
                                    }
                                }
                            }

                            // Close button overlay
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                IconButton(
                                    onClick = { isShowingStats.value = false },
                                    modifier = Modifier.background(
                                        Color.White.copy(alpha = 0.9f),
                                        shape = MaterialTheme.shapes.small
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Close",
                                        tint = Color(0xFF005C5C)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF005C5C),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.weight(2f)
        )
    }
}

