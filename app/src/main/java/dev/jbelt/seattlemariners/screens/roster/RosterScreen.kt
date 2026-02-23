package dev.jbelt.seattlemariners.screens.roster

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.jbelt.seattlemariners.viewmodel.RosterViewModel

@Composable
fun RosterScreen(
    viewModel: RosterViewModel = hiltViewModel(),
) {
    val rosterResponse = viewModel.roster_response.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Seattle Mariners Roster",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )

        RosterList(athleteGroups = rosterResponse?.athletes ?: emptyList())
    }
}
