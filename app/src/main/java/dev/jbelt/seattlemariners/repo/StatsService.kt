package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.GameScheduleResponse
import retrofit2.http.GET

interface StatsService {

    @GET("schedule/?sportId=1&teamId=136")
    suspend fun getMarinersGameToday(): GameScheduleResponse
}