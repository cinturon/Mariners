package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.GameScheduleResponse
import dev.jbelt.seattlemariners.models.LiveData
import dev.jbelt.seattlemariners.models.LiveFeedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StatsService {

    @GET("api/v1/schedule/?sportId=1&teamId=136")
    suspend fun getMarinersGameToday(): GameScheduleResponse

    @GET("api/v1.1/game/{gamePk}/feed/live")
    suspend fun getLiveFeed(
        @Path("gamePk") pk: String,
        @Query("timeStamp") timeStamp: String
    ): LiveFeedResponse
}
