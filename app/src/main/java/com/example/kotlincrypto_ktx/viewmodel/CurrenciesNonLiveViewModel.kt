package com.example.kotlincrypto_ktx.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Currency
import com.example.domain.params.RepoParams
import com.example.domain.usecase.BaseUseCase
import com.example.domain.usecase.GetPricesNonLiveDataUseCase
import com.example.domain.util.Failure
import com.example.kotlincrypto_ktx.model.CurrencyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * View Model class to highlight difference in updating logic on ui module vs data module.
 * Here we are using a flag ideally from the client to determine if liveupdating should be applied.
 * if applied we start a loop that only continues if the current coroutine scope is active. This is internally managed by the
 * view model lifecycle when using the correct viewModelScope field.
 * This is a nice way to control what to call and how to call it. This can be split out into several use cases if desired to make
 * the calls more granular. Or we can continue to use the RepoParams class to tell the repository what we want returned and continue using a
 * single use case.
 */
class CurrenciesNonLiveViewModel(private val getPricesNonLiveDataUseCase: GetPricesNonLiveDataUseCase) : ViewModel() {


    private var _currencyModels = MutableLiveData<List<CurrencyModel>>()

    val currencyModels: LiveData<List<CurrencyModel>>
        get() = _currencyModels


    suspend fun load(liveUpdate: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            if(!liveUpdate) {
                getPricesNonLiveDataUseCase.execute(RepoParams(false)).either(::handleError, ::handleSuccess)
            }else{
                while(this.isActive){
                    Log.d(this::class.java.canonicalName, "Calling refresh to api for live updating")
                    getPricesNonLiveDataUseCase.execute(RepoParams(true)).either(::handleError, ::handleSuccess)
                    delay(2_000)
                    handleSuccess(emptyList())
                    Log.d(this::class.java.canonicalName, "Reset for visual confirmation")
                    delay(2_000)
                }
            }
        }
    }

    private fun handleSuccess(list: List<Currency>) {
        _currencyModels.postValue(list.map { CurrencyModel.get(it) })
    }

    private fun handleError(failure: Failure){
    }
}