package com.example.kotlincrypto_ktx.viewmodel

import androidx.lifecycle.*
import com.example.data.repository.NomicRepository
import com.example.kotlincrypto_ktx.model.CurrencyModel

class CurrenciesViewModel(private val nomicRepository: NomicRepository) : ViewModel() {


    fun loadCurrencies() : LiveData<List<CurrencyModel>> {
        return nomicRepository.getPrices().map{ list -> list.map { CurrencyModel.get(it) } }
    }
}