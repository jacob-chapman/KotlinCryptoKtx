package com.example.newsfeature.data.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NewsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", "38236091180a464787f4a1a05f3a63a5")
            .build()
        Log.d("Network", "$request.url()")
        return chain.proceed(request)
    }
}