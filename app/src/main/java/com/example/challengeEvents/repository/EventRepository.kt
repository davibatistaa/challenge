package com.example.challengeEvents.repository

import com.example.challengeEvents.data.api.EventApi
import com.example.challengeEvents.model.Event

class EventRepository(private val eventApi: EventApi) {

    suspend fun getEvents(): List<Event> {
        return eventApi.getEvents()
    }
}
