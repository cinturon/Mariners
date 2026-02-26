package dev.jbelt.seattlemariners.screens.home

import android.R
import android.R.attr.end
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.jbelt.seattlemariners.viewmodel.LiveGameViewModel

@Composable
fun LiveGameCard(
    viewModel: LiveGameViewModel = hiltViewModel(),
    gamePk: Int?,
    modifier: Modifier = Modifier
) {

    val currentPlay by viewModel.currentPlay.collectAsStateWithLifecycle()

    LaunchedEffect(gamePk) {
        if (gamePk != null) {
            viewModel.connect(gamePk)
        }
    }

    val play = currentPlay

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1A1A1A)
        ),
        border = CardDefaults.outlinedCardBorder(
            true
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            if (play != null) {

                // Inning
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${play.about?.halfInning?.capitalize() ?: ""} ${play.about?.inning}" ,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.LightGray
                    )
                    Text(
                        text = "Outs: ${play.count?.outs}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.LightGray
                    )
                }

                // Divider
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFF333333))
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Game Info Section
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    val batterName = play.matchup?.batter?.fullName ?: "Unknown"
                    val pitcherName = play.matchup?.pitcher?.fullName ?: "Unknown"

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Batter
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Hitter: ",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.LightGray,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = batterName,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF4CAF50),
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Pitcher
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Pitcher: ",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.LightGray,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = pitcherName,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF4CAF50),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }



                    // Score
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Score",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.LightGray
                        )
                        Text(
                            text = "${play.result?.awayScore} - ${play.result?.homeScore}",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White
                        )
                    }

                    // Count and Outs
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Count: ${play.count?.balls}-${play.count?.strikes}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.LightGray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Divider
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFF333333))
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Play Description
                Text(
                    text = play.result?.description ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(
                    text = "Waiting for next update...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}