package com.example.kotlincrypto_ktx.viewmodel

import androidx.lifecycle.*
import com.example.data.model.Currency
import com.example.data.repository.NomicRepository

class CurrenciesViewModel(private val nomicRepository: NomicRepository) : ViewModel() {


    fun loadCurrencies() : LiveData<List<Currency>> {
        return nomicRepository.getPrices()
    }
}