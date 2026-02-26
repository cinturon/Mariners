package dev.jbelt.seattlemariners.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jbelt.seattlemariners.models.CurrentPlay
import dev.jbelt.seattlemariners.models.GameUpdate
import dev.jbelt.seattlemariners.repo.LiveGameService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LiveGameViewModel @Inject constructor(
    private val liveGameService: LiveGameService
) : ViewModel() {

    private val _currentPlay = MutableStateFlow<CurrentPlay?>(null)
    val currentPlay: StateFlow<CurrentPlay?> = _currentPlay

    fun connect(gamePk: Int) {
        viewModelScope.launch {
            // Enable reconnection before starting a new session
            liveGameService.enableReconnect()
            liveGameService.initSession(gamePk).collect { newUpdate ->
                _currentPlay.value = newUpdate
            }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            liveGameService.closeSession()
            _currentPlay.value = null
        }
    }

    fun reconnect(gamePk: Int) {
        // Disconnect first, then connect again
        viewModelScope.launch {
            liveGameService.closeSession()
            // enableReconnect is called within connect()
            connect(gamePk)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            liveGameService.closeSession()
        }
    }
}