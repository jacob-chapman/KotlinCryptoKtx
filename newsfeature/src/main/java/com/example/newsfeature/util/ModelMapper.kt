package com.example.newsfeature.util

import com.example.newsfeature.data.model.Article
import com.example.newsfeature.domain.entity.NewsArticle
import com.example.newsfeature.domain.entity.NewsSource

class ModelMapper {
    companion object {
        fun mapToNewsArticle(apiNewsArticle: Article) : NewsArticle {
            return NewsArticle(
                source = NewsSource(
                    set(apiNewsArticle.source?.id),
                    set(apiNewsArticle.source?.name)
                ),
                author = set(apiNewsArticle.author),
                title = set(apiNewsArticle.title),
                description = set(apiNewsArticle.description),
                url = set(apiNewsArticle.url),
                urlToImage = set(apiNewsArticle.urlToImage),
                publishedAt = set(apiNewsArticle.publishedAt),
                content = set(apiNewsArticle.content)
            )
        }

        fun set(apiValue: String?) : String = apiValue ?: ""
    }
}