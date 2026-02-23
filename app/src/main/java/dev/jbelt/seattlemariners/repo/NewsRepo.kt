package dev.jbelt.seattlemariners.repo

import dev.jbelt.seattlemariners.models.HeadlinesResponse
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newsService: NewsService
){
    suspend fun getArticle(articleId: Long): HeadlinesResponse {
        return newsService.getArticle(articleId)
    }
}