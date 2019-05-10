package com.example.data.api

import com.example.data.model.Currency
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NomicsApi {

    @GET("prices")
    suspend fun getPrices() : Deferred<List<Currency>>

}