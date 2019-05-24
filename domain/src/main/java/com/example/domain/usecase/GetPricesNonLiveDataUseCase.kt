package com.example.domain.usecase

import com.example.domain.entity.Currency
import com.example.domain.params.RepoParams
import com.example.domain.repository.PricesRepository
import com.example.domain.util.Either
import com.example.domain.util.Failure

class GetPricesNonLiveDataUseCase(private val pricesRepository: PricesRepository) : BaseUseCase<List<Currency>, RepoParams>(){
    override suspend fun execute(params: RepoParams): Either<Failure, List<Currency>>  = pricesRepository.loadPrices(params)
}