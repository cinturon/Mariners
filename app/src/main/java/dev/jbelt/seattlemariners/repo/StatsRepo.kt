package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.Game
import dev.jbelt.seattlemariners.models.LiveData
import dev.jbelt.seattlemariners.models.LiveFeedResponse
import javax.inject.Inject

class StatsRepo @Inject constructor(
    private val statsService: StatsService
) {

    suspend fun getTodaysGame(): Game? {
        return statsService.getMarinersGameToday().dates?.firstOrNull()?.games?.firstOrNull()
    }

    suspend fun getLiveFeed(gamePk: String, timeStamp: String): LiveFeedResponse {
        return statsService.getLiveFeed(gamePk, timeStamp)
    }
}