package com.example.newsfeature.domain.usecase

import core.BaseUseCase
import core.Either
import core.Failure
import com.example.newsfeature.domain.entity.NewsArticle
import com.example.newsfeature.domain.repo.NewsRepository
import javax.inject.Inject

class GetUSNewsUseCase @Inject constructor(private val newsRepository: NewsRepository): BaseUseCase<List<NewsArticle>, BaseUseCase.None>(){
    override suspend fun execute(params: None): Either<Failure, List<NewsArticle>> = newsRepository.loadUsNews(params = params)
}
