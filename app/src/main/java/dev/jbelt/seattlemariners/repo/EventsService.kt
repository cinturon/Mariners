package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.EventsSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsService {

    @GET("discovery/v2/events")
    suspend fun getEvents(
        @Query("attractionId") attractionId: String,
        @Query("apikey") apiKey: String,
        @Query("sort") sort: String
    ): EventsSearchResponse


    @GET("discovery/v2/events")
    suspend fun getUpcomingEvents(
        @Query("attractionId") attractionId: String,
        @Query("apikey") apiKey: String,
        @Query("startDateTime") startDateTime: String,
        @Query("endDateTime") endDateTime: String,
    ): EventsSearchResponse
}

