package com.example.kotlincrypto_ktx.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.data.repository.NomicRepository
import com.example.kotlincrypto_ktx.model.CurrencyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class CurrenciesViewModel(private val nomicRepository: NomicRepository) : ViewModel() {

    private var _currencyModels = liveData {
        Log.d(this::class.qualifiedName, "getting source from viewmodel to repo")
        emitSource(loadCurrencies())
    }
    val currencyModels: LiveData<List<CurrencyModel>>
        get() = _currencyModels

    private suspend  fun loadCurrencies() : LiveData<List<CurrencyModel>>{
            return nomicRepository.getPrices(viewModelScope.coroutineContext + Dispatchers.IO)
                .map { list -> list
                    .sortedByDescending { it.price.toFloat() }
                    .map { CurrencyModel.get(it) } }

    }
}