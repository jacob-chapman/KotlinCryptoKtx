package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.entity.Currency
import com.example.domain.params.RepoParams
import com.example.domain.repository.PricesRepository
import com.example.domain.util.Either
import com.example.domain.util.Failure

class GetPricesLiveDataUseCase(private val pricesRepository: PricesRepository) :  BaseUseCase<LiveData<List<Currency>>, RepoParams>(){

    override suspend fun execute(params: RepoParams): Either<Failure, LiveData<List<Currency>>> = pricesRepository.loadPricesLive(params)
}