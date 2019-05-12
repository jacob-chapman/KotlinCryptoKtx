package com.example.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.data.api.NomicsApi
import com.example.data.model.Currency

class NomicRepository(private val nomicsApi: NomicsApi) : BaseRepository() {

    fun getPrices() = liveData<List<Currency>> {
        try{
            val pricesResponse = apiCall(
                call = { nomicsApi.getPrices().await() },
                errorMessage = "Failed to get Prices from Network"
            )
            emit(pricesResponse!!.sortedByDescending { it.price.toFloat() })
        } catch (ex: Exception) {
            Log.d(this::class.java.simpleName, ex.message)
        }
    }

}