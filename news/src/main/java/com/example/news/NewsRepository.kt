package com.example.news

import android.util.Log
import com.example.domain.entity.NewsArticle
import com.example.domain.entity.NewsSource
import com.example.domain.params.RepoParams
import com.example.domain.repository.NewsRepository
import com.example.domain.usecase.BaseUseCase
import com.example.domain.util.Either
import com.example.domain.util.Failure
import com.example.news.api.NewsApi
import com.example.news.model.Article
import dagger.Reusable
import javax.inject.Inject

@Reusable
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

class ModelMapper {
    companion object {
        fun mapToNewsArticle(apiNewsArticle: Article) : NewsArticle {
            return NewsArticle(
                source = NewsSource(set(apiNewsArticle.source?.id), set(apiNewsArticle.source?.name)),
                author = set(apiNewsArticle.author),
                title = set(apiNewsArticle.title),
                description = set(apiNewsArticle.description),
                url =  set(apiNewsArticle.url),
                urlToImage = set(apiNewsArticle.urlToImage),
                publishedAt = set(apiNewsArticle.publishedAt),
                content = set(apiNewsArticle.content)
            )
        }

        fun set(apiValue: String?) : String = apiValue ?: ""
    }
}