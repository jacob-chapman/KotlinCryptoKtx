package com.example.domain.repository

import com.example.domain.entity.NewsArticle
import com.example.domain.params.RepoParams
import com.example.domain.usecase.BaseUseCase
import com.example.domain.util.Either
import com.example.domain.util.Failure

interface NewsRepository {
    suspend fun loadUsNews(params: BaseUseCase.None) : Either<Failure, List<NewsArticle>>
}