package com.example.challengeEvents.data.api

import com.example.challengeEvents.model.Event
import retrofit2.http.GET

interface EventApi {
    @GET("events")
    suspend fun getEvents(): List<Event>
}
