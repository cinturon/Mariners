package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.Article
import dev.jbelt.seattlemariners.models.NewsResponse
import dev.jbelt.seattlemariners.models.RosterResponse
import javax.inject.Inject

class ApiRepo @Inject constructor(
    private val apiService: ApiService
){
    suspend fun getRosterResponse(): RosterResponse {
        return apiService.getRosterResponse()
    }

    suspend fun getNews(): NewsResponse {
        return apiService.getNews()
    }
}