package com.example.kotlincrypto_ktx.viewmodel

import androidx.lifecycle.*
import com.example.domain.entity.Currency
import com.example.domain.usecase.BaseUseCase
import com.example.domain.usecase.GetPricesUseCase
import com.example.domain.util.Failure
import com.example.kotlincrypto_ktx.model.CurrencyModel
import kotlinx.coroutines.launch

class CurrenciesViewModel(private val getPricesUseCase: GetPricesUseCase) : ViewModel() {

    fun loadCurrencies() = liveData<List<CurrencyModel>> {
        viewModelScope.launch {
            getPricesUseCase.execute(BaseUseCase.None()).either(::handleError){
                launch{
                    emitSource(Transformations.map(it, ::handleSuccess))
                }
            }
        }
    }

    private fun handleSuccess(list: List<Currency>): List<CurrencyModel> {
        return list.map { CurrencyModel.get(it) }
    }

    private fun handleError(failure: Failure){
    }

}