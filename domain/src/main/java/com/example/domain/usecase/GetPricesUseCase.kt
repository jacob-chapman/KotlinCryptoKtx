package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.entity.Currency
import com.example.domain.repository.PricesRepository
import com.example.domain.util.Either
import com.example.domain.util.Failure
import kotlin.coroutines.CoroutineContext

class GetPricesUseCase(private val pricesRepository: PricesRepository) :  BaseUseCase<LiveData<List<Currency>>, BaseUseCase.None>(){

    override suspend fun execute(params: None): Either<Failure, LiveData<List<Currency>>> = pricesRepository.loadPrices()
}