package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.entity.Currency
import com.example.domain.util.Either
import com.example.domain.util.Failure

interface PricesRepository {
    suspend fun loadPrices() : Either<Failure, LiveData<List<Currency>>>
}