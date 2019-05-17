package com.example.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.data.api.NomicsApi
import com.example.data.db.NomicsDb
import com.example.data.model.Currency
import com.example.data.model.Dashboard
import com.example.domain.repository.PricesRepository
import com.example.domain.util.Either
import com.example.domain.util.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlin.coroutines.CoroutineContext

class NomicRepository(private val nomicsApi: NomicsApi, private val nomicsDb: NomicsDb) : BaseRepository(), PricesRepository {

    //todo add repo and use case
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

    override suspend fun loadPrices(): Either<Failure, LiveData<List<com.example.domain.entity.Currency>>> {

        return try {
            Either.Success(liveData(Dispatchers.IO) {
                    Log.d(this::class.qualifiedName, "returning database source")
                    emitSource(nomicsDb.pricesDao().getAllCurrencies().map { list -> list.map { ModelMapper.mapToCurrency(it) }})
                    val pricesResponse = apiCall(
                        call = { nomicsApi.getPricesAsync().await() },
                        errorMessage = "Failed to get Prices from Network"
                    )
                    delay(5_000)
                    Log.d(this::class.qualifiedName, "deleting...")
                    nomicsDb.pricesDao().deleteAll()
                    delay(5_000)
                    Log.d(this::class.qualifiedName, "adding/updating api response to db")
                    if (pricesResponse != null)
                        nomicsDb.pricesDao().insertAll(*pricesResponse.toTypedArray())
            })
        } catch(exception: Throwable) {
            Either.Error(Failure.ServerError)
        }
    }
}

class ModelMapper {
    companion object {
        fun mapToCurrency(dbCurrency: Currency) : com.example.domain.entity.Currency {
            return com.example.domain.entity.Currency(
                dbCurrency.name,
                dbCurrency.price
            )
        }
    }
}