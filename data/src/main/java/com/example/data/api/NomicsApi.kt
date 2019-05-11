package com.example.data.api

import com.example.data.model.Currency
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NomicsApi {

    @GET("/v1/prices")
    fun getPrices() : Deferred<Response<List<Currency>>>

}