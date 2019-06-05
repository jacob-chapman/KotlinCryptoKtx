package com.example.newsfeature.data.repo

import android.util.Log
import core.BaseRepository
import core.BaseUseCase
import core.Either
import core.Failure
import com.example.newsfeature.data.api.NewsApi
import com.example.newsfeature.domain.entity.NewsArticle
import com.example.newsfeature.domain.repo.NewsRepository
import com.example.newsfeature.util.ModelMapper
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : BaseRepository(), NewsRepository {

    override suspend fun loadUsNews(params: BaseUseCase.None): Either<Failure, List<NewsArticle>> {
        Log.d(this::class.java.canonicalName, "Loading US News")
        return try {
            val newsReponse = apiCall(
                call = { newsApi.getTopUsHeadlines().await() },
                errorMessage = "Failed to get News from Network"
            )

            if (newsReponse != null) {
                Either.Success(newsReponse.articles.map { ModelMapper.mapToNewsArticle(it) })
            } else {
                Either.Error(Failure.ServerError)
            }

        }catch(exception: Throwable){
            Either.Error(Failure.NetworkConnection)
        }
    }
}
