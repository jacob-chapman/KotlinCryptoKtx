package com.example.news.api

import com.example.news.model.NewsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi  {

    @GET("/v2/top-headlines?country=us")
    fun getTopUsHeadlines() : Deferred<Response<NewsResponse>>
}