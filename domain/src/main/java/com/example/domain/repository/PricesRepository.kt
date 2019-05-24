package com.example.domain.repository

import com.example.domain.entity.Currency
import com.example.domain.params.RepoParams
import com.example.domain.util.Either
import com.example.domain.util.Failure

interface PricesRepository {
    suspend fun loadPrices(params: RepoParams) : Either<Failure, List<Currency>>
}