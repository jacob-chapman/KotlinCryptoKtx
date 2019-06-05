package com.example.newsfeature.di

import android.content.Context
import com.example.newsfeature.data.api.NewsApi
import com.example.newsfeature.data.api.NewsInterceptor
import com.example.newsfeature.data.repo.NewsRepositoryImpl
import com.example.newsfeature.domain.repo.NewsRepository
import com.example.newsfeature.ui.USNewsViewModel
import dagger.*
import network.NetworkModule
import retrofit2.Retrofit

@Component(modules = [NewsNetworkModule::class, NetworkModule::class])
interface NewsFeatureComponent {

    val newsViewModel : USNewsViewModel

}

@Module
class NewsNetworkModule {

    @Reusable
    @Provides
    fun providesNewsApi(retrofit: Retrofit) : NewsApi = retrofit.create(NewsApi::class.java)

    @Reusable
    @Provides
    fun provideNewsRepo(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository = newsRepositoryImpl
}
