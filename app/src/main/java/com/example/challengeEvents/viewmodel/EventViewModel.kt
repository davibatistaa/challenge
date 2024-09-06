package com.example.challengeEvents.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengeEvents.data.api.EventApi
import com.example.challengeEvents.model.Event
import com.example.challengeEvents.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchEvents()
    }

    fun fetchEvents() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val fetchedEvents = repository.getEvents()
                _events.value = fetchedEvents
            } catch (e: Exception) {
                _error.value = "Erro ao carregar eventos. Tente novamente."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
