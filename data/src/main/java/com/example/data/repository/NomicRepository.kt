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

    override suspend fun loadPricesLive(): Either<Failure, LiveData<List<com.example.domain.entity.Currency>>> {

        return try {
            Either.Success(liveData(Dispatchers.IO) {
                    Log.d(this::class.qualifiedName, "returning database source")
                    emitSource(nomicsDb.pricesDao().getAllCurrencies().map { list -> list.map { ModelMapper.mapToCurrency(it) }})
                    refreshPricesFromApi()
                    return@liveData
            })
        } catch(exception: Throwable) {
            Either.Error(Failure.ServerError)
        }
    }

    private suspend fun refreshPricesFromApi() {
        repeat(10) {
            Log.d("Refreshing from Api #", "$it")
            val pricesResponse = apiCall(
                call = { nomicsApi.getPricesAsync().await() },
                errorMessage = "Failed to get Prices from Network"
            )
            //lets just pause for a few seconds
            delay(5_000)
            //lets go ahead and delete all so we can visually see the update
            Log.d(this::class.qualifiedName, "deleting...")
            nomicsDb.pricesDao().deleteAll()
            //everything is deleted, lets pause for another couple seconds so we can see it
            delay(2_000)
            //now that we could visually see the update lets go ahead and update the db
            Log.d(this::class.qualifiedName, "adding/updating api response to db")
            if (pricesResponse != null)
                nomicsDb.pricesDao().insertAll(*pricesResponse.toTypedArray())
        }
    }

    override suspend fun loadPricesNonLive(): Either<Failure, List<com.example.domain.entity.Currency>> {
        return try{
            Either.Success(nomicsDb.pricesDao().getAllCurrenciesNonLiveData().map { ModelMapper.mapToCurrency(it) })
        } catch(exception: Throwable){
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