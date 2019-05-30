package com.example.domain.usecase

import com.example.domain.entity.NewsArticle
import com.example.domain.params.RepoParams
import com.example.domain.repository.NewsRepository
import com.example.domain.util.Either
import com.example.domain.util.Failure
import com.sun.net.httpserver.Authenticator
import javax.inject.Inject

class GetUSNewsUseCase @Inject constructor(private val newsRepository: NewsRepository): BaseUseCase<List<NewsArticle>, BaseUseCase.None>(){
    override suspend fun execute(params: None): Either<Failure, List<NewsArticle>> = newsRepository.loadUsNews(params = params)
}
