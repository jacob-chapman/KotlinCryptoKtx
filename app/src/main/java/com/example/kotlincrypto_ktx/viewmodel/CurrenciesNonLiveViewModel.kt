package com.example.kotlincrypto_ktx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Currency
import com.example.domain.usecase.BaseUseCase
import com.example.domain.usecase.GetPricesNonLiveDataUseCase
import com.example.domain.util.Failure
import com.example.kotlincrypto_ktx.model.CurrencyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrenciesNonLiveViewModel(private val getPricesNonLiveDataUseCase: GetPricesNonLiveDataUseCase) : ViewModel() {


    private var _currencyModels = MutableLiveData<List<CurrencyModel>>()

    val currencyModels: LiveData<List<CurrencyModel>>
        get() = _currencyModels


    suspend fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            getPricesNonLiveDataUseCase.execute(BaseUseCase.None()).either(::handleError, ::handleSuccess)
        }
    }

    private fun handleSuccess(list: List<Currency>) {
        _currencyModels.postValue(list.map { CurrencyModel.get(it) })
    }

    private fun handleError(failure: Failure){
    }
}