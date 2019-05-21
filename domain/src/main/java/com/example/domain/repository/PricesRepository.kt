package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.entity.Currency
import com.example.domain.params.RepoParams
import com.example.domain.util.Either
import com.example.domain.util.Failure

interface PricesRepository {
    suspend fun loadPricesLive(params: RepoParams) : Either<Failure, LiveData<List<Currency>>>

    suspend fun loadPricesNonLive(params: RepoParams) : Either<Failure, List<Currency>>
}