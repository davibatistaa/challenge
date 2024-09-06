package com.example.challengeEvents.data.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val client = OkHttpClient.Builder()
        .addInterceptor(ErrorInterceptor())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
        .client(client) // Adicione o cliente configurado com o interceptor
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
