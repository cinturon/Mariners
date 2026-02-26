package dev.jbelt.seattlemariners.repo

import androidx.room.Query
import dev.jbelt.seattlemariners.models.HeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {
    @GET("{id}")
    suspend fun getArticle(@Path("id") id: Long): HeadlinesResponse

}