package dev.jbelt.seattlemariners.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jbelt.seattlemariners.models.EventsResponse
import dev.jbelt.seattlemariners.repo.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    private val _eventsList = MutableStateFlow<List<EventsResponse>>(emptyList())
    val eventsList: StateFlow<List<EventsResponse>> get() = _eventsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

//    init {
//        fetchMarinersEvents()
//    }

    fun fetchMarinersEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = eventsRepository.getMarinersEvents()

            result.onSuccess { events ->
                _eventsList.value = events
                _isLoading.value = false
            }

            result.onFailure { exception ->
                _error.value = exception.message ?: "Unknown error occurred"
                _isLoading.value = false
            }
        }
    }

    fun fetchUpcomingEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = eventsRepository.getUpcomingEvents()
            result.onSuccess { events ->
                _eventsList.value = events
                _isLoading.value = false
            }

            result.onFailure { exception ->
                _error.value = exception.message ?: "Unknown error occurred"
                _isLoading.value = false
            }
        }
    }
}

