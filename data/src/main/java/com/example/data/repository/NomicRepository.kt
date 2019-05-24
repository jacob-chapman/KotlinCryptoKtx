package com.example.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.data.api.NomicsApi
import com.example.data.db.NomicsDb
import com.example.data.model.Dashboard
import com.example.data.util.ModelMapper
import com.example.domain.entity.Currency
import com.example.domain.params.RepoParams
import com.example.domain.repository.PricesRepository
import com.example.domain.util.Either
import com.example.domain.util.Failure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext
import kotlin.system.exitProcess

/**
 * The loadPrices appears to have less code in the repo, but more of the logic for looping lives in the view model. This could be a nice todo in order to figure out how it could be handled here
 * However, now it solely relies on the params to just say whether or not it should hit the api. It has no real clue about any live updating. As it needs to return a value at all times instead of a
 * live data object that can be updated from the repo layer
 */
class NomicRepository(private val nomicsApi: NomicsApi, private val nomicsDb: NomicsDb) : BaseRepository(), PricesRepository {

    //todo add repo and use case
    suspend fun getDashboardForCurrency(currency: String, coroutineContext: CoroutineContext) = liveData<Dashboard>(coroutineContext) {
        Log.d(this::class.java.canonicalName, "returning database source")
        emitSource(nomicsDb.dashboardDao().getDashboardForCurrency(currency))
        val dashboardResponse = apiCall(
            call = { nomicsApi.getDashboardAsync().await() },
            errorMessage = "Failed to get Dashboards from Network"
        )

        Log.d(this::class.java.canonicalName, "adding/updating api response to db")
        if(dashboardResponse != null)
            nomicsDb.dashboardDao().insertAll(*dashboardResponse.toTypedArray())
    }

    private suspend fun pricesApiCall(updateDb: Boolean) : List<Currency> {
        val pricesResponse = apiCall(
            call = { nomicsApi.getPricesAsync().await() },
            errorMessage = "Failed to get Prices from Network"
        )
        if(pricesResponse != null){
            if(updateDb) nomicsDb.pricesDao().insertAll(*pricesResponse.toTypedArray())
            return pricesResponse.map { ModelMapper.mapToCurrency(it) }
        }

        return emptyList()
    }

    private suspend fun refreshPricesFromApi() {
            Log.d(this::class.java.canonicalName,"Refreshing from Api")
            //lets just pause for a few seconds
            delay(5_000)
            //lets go ahead and delete all so we can visually see the update
            Log.d(this::class.java.canonicalName, "deleting...")
            nomicsDb.pricesDao().deleteAll()
            //everything is deleted, lets pause for another couple seconds so we can see it
            delay(2_000)
            //now that we could visually see the update lets go ahead and update the db
            Log.d(this::class.java.canonicalName, "adding/updating api response to db")
            pricesApiCall(updateDb = true)
    }

    override suspend fun loadPrices(params: RepoParams): Either<Failure, List<Currency>> {
        return try{
            if(params.refreshFromApi){
                //client is telling us to refresh from the ui first
                val currencyPrices = pricesApiCall(true)
                if(currencyPrices.isNullOrEmpty()) {
                    Either.Error(Failure.NetworkConnection)
                }
            }
            Either.Success(nomicsDb.pricesDao().getAllCurrenciesNonLiveData().map { ModelMapper.mapToCurrency(it) })

        } catch(exception: Throwable){
            Either.Error(Failure.ServerError)
        }
    }
}

