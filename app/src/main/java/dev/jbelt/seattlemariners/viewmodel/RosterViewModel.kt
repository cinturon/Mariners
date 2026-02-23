package dev.jbelt.seattlemariners.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jbelt.seattlemariners.models.RosterResponse
import dev.jbelt.seattlemariners.repo.ApiRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RosterViewModel @Inject constructor(
    private val apiRepo: ApiRepo
) : ViewModel() {
    private val _roster_response = MutableStateFlow<RosterResponse?>(null)
    val roster_response: StateFlow<RosterResponse?> get() = _roster_response

    init {
        fetchRosterResponse()
    }

    private fun fetchRosterResponse() {
        viewModelScope.launch {
            try {
                val response = apiRepo.getRosterResponse()
                _roster_response.value = response
            } catch (e: Exception) {
                Log.e("RosterViewModel", "Error fetching roster", e)
            }
        }
    }
}
