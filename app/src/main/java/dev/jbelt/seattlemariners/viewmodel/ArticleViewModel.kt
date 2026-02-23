package dev.jbelt.seattlemariners.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jbelt.seattlemariners.models.HeadlinesResponse
import dev.jbelt.seattlemariners.repo.NewsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ArticleViewModel"

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepo
) : ViewModel() {
    private val _article = MutableStateFlow<HeadlinesResponse?>(null)
    val article: StateFlow<HeadlinesResponse?> get() = _article

    fun fetchArticle(articleId: Long) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Fetching article with ID: $articleId")
                val articleResponse = newsRepository.getArticle(articleId)
                Log.d(TAG, "Article fetched successfully. Headlines count: ${articleResponse.headlines?.size}")
                _article.value = articleResponse
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching article $articleId: ${e.message}", e)
                e.printStackTrace()
            }
        }
    }
}
