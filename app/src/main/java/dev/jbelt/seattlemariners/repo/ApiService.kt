package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.Article
import dev.jbelt.seattlemariners.models.NewsResponse
import dev.jbelt.seattlemariners.models.RosterResponse
import retrofit2.http.GET


interface ApiService {

    @GET("teams/sea/roster")
    suspend fun getRosterResponse(): RosterResponse

    @GET("news")
    suspend fun getNews(): NewsResponse
}