package com.example.data.api

import com.example.data.model.Currency
import com.example.data.model.Dashboard
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NomicsApi {

    @GET("/v1/prices")
    fun getPricesAsync() : Deferred<Response<List<Currency>>>

    @GET("/v1/dashboard")
    fun getDashboardAsync() : Deferred<Response<List<Dashboard>>>
}