package com.example.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.api.NomicsApi
import com.example.data.model.Currency

class NomicRepository(private val nomicsApi: NomicsApi) {

    suspend fun getPrices() : LiveData<List<Currency>> {
        var prices = MutableLiveData<List<Currency>>()
        try{
            val networkResponse = nomicsApi.getPrices().await()
            if (networkResponse.isEmpty())
                return prices

            prices.value = networkResponse
            return prices
        } catch (ex: Exception) {
            Log.d(this::class.java.simpleName, ex.message)
        }

        return prices
    }

}