package dev.jbelt.seattlemariners.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jbelt.seattlemariners.models.Game
import dev.jbelt.seattlemariners.repo.StatsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val statsRepo: StatsRepo
) : ViewModel(){

    private val _todayGame = MutableStateFlow<Game?>(null)
    private val _isLoading = MutableStateFlow(false)

    val todaysGame: StateFlow<Game?> get() = _todayGame
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        fetchTodaysGame()
    }

    private fun fetchTodaysGame() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val gameResponse = statsRepo.getTodaysGame()
                _todayGame.value = gameResponse
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshGame() {
        fetchTodaysGame()
    }

}
