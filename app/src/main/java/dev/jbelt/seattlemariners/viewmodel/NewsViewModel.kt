package dev.jbelt.seattlemariners.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jbelt.seattlemariners.models.NewsResponse
import dev.jbelt.seattlemariners.repo.ApiRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: ApiRepo
) : ViewModel() {

    private val _news = MutableStateFlow<NewsResponse?>(null)

    val news: StateFlow<NewsResponse?> get() = _news

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = newsRepository.getNews()
                _news.value = response
            } catch (e: Exception) {
                // Handle error
                Log.e("NewsViewModel", "Error fetching news", e)
            }
        }
    }
}