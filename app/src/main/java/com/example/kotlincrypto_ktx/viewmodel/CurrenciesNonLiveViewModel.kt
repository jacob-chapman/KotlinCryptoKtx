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
                    Log.d(this::class.qualifiedName, "Calling refresh to api for live updating")
                    getPricesNonLiveDataUseCase.execute(RepoParams(true)).either(::handleError, ::handleSuccess)
                    delay(2_000)
                    handleSuccess(emptyList())
                    Log.d(this::class.simpleName, "Reset for visual confirmation")
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