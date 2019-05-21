package com.example.kotlincrypto_ktx.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Dashboard
import com.example.data.repository.NomicRepository
import kotlinx.coroutines.Dispatchers

class DashboardViewModel(private val nomicRepository: NomicRepository) : ViewModel() {


    suspend fun loadDashboard(currencyName: String) : LiveData<Dashboard>{
        return nomicRepository.getDashboardForCurrency(currencyName, viewModelScope.coroutineContext + Dispatchers.IO)
    }

}