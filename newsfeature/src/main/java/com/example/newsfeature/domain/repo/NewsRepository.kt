package com.example.newsfeature.domain.repo

import com.example.newsfeature.domain.entity.NewsArticle
import core.BaseUseCase
import core.Either
import core.Failure


interface NewsRepository {
    suspend fun loadUsNews(params: BaseUseCase.None) : Either<Failure, List<NewsArticle>>
}