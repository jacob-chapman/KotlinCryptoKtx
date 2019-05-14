package com.example.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.example.data.api.NomicsApi
import com.example.data.db.NomicsDb
import com.example.data.model.Currency
import com.example.data.model.Dashboard
import kotlin.coroutines.CoroutineContext

class NomicRepository(private val nomicsApi: NomicsApi, private val nomicsDb: NomicsDb) : BaseRepository() {

    suspend fun getPrices(coroutineContext: CoroutineContext) = liveData<List<Currency>>(coroutineContext) {
        try{
            Log.d(this::class.qualifiedName, "returning database source")
            emitSource(nomicsDb.pricesDao().getAllCurrencies())
            val pricesResponse = apiCall(
                call = { nomicsApi.getPricesAsync().await() },
                errorMessage = "Failed to get Prices from Network"
            )

            Log.d(this::class.qualifiedName, "adding/updating api response to db")
            if(pricesResponse != null)
                nomicsDb.pricesDao().insertAll(*pricesResponse.toTypedArray())

        } catch (ex: Exception) {
            Log.d(this::class.java.simpleName, ex.message)
        }
    }


    suspend fun getDashboardForCurrency(currency: String, coroutineContext: CoroutineContext) = liveData(coroutineContext) {
        Log.d(this::class.qualifiedName, "returning database source")
        emitSource(nomicsDb.dashboardDao().getDashboardForCurrency(currency))
        val dashboardResponse = apiCall(
            call = { nomicsApi.getDashboardAsync().await() },
            errorMessage = "Failed to get Dashboards from Network"
        )

        Log.d(this::class.qualifiedName, "adding/updating api response to db")
        if(dashboardResponse != null)
            nomicsDb.dashboardDao().insertAll(*dashboardResponse.toTypedArray())
    }

}