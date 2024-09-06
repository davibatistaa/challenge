package com.example.challengeEvents.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (!response.isSuccessful) {
            // Trate o erro conforme necessário
            throw IOException("Network Error: ${response.code} - ${response.message}")
        }

        return response
    }
}
