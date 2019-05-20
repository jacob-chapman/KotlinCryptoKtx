package com.example.domain.usecase

import com.example.domain.entity.Currency
import com.example.domain.repository.PricesRepository
import com.example.domain.util.Either
import com.example.domain.util.Failure

class GetPricesNonLiveDataUseCase(val pricesRepository: PricesRepository) : BaseUseCase<List<Currency>, BaseUseCase.None>(){
    override suspend fun execute(params: None): Either<Failure, List<Currency>>  = pricesRepository.loadPricesNonLive()
}