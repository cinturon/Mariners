package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.Game
import javax.inject.Inject

class StatsRepo @Inject constructor(
    private val statsService: StatsService
) {

    suspend fun getTodaysGame(): Game? {
        return statsService.getMarinersGameToday().dates?.firstOrNull()?.games?.firstOrNull()

    }
}