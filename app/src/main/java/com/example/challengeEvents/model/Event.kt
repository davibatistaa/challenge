package com.example.challengeEvents.model

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
    val date: Long,
    val people: List<String>
)
