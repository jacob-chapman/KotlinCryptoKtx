package com.example.newsfeature.data.api

import com.example.newsfeature.data.model.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi  {
    @GET("/v2/top-headlines?country=us")
    fun getTopUsHeadlines() : Deferred<Response<NewsResponse>>
}