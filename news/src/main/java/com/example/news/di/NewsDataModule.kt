package com.example.news.di

import android.util.Log
import com.example.domain.repository.NewsRepository
import com.example.news.NewsRepositoryImpl
import com.example.news.api.NewsApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named


@Module
object NewsDataModule {

    private const val news_base_url = "https://newsapi.org"

    @JvmStatic
    @Provides
    @Named("news-client")
    fun provideNewsHttpClient() : OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("X-Api-Key", "38236091180a464787f4a1a05f3a63a5")
                        .build()
                    Log.d("Network", "$request.url()")
                    chain.proceed(request)
                }
                .build()

    @JvmStatic
    @Provides
    @Named("news-retrofit")
    fun provideNewsRetrofit(moshiConverterFactory: MoshiConverterFactory,
        @Named("news-client") okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .baseUrl(news_base_url)
                .build()

    @JvmStatic
    @Provides
    fun providesNewsApi(@Named("news-retrofit")retrofit: Retrofit) : NewsApi = retrofit.create(NewsApi::class.java)

    @JvmStatic
    @Provides
    fun provideNewsRepo(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository = newsRepositoryImpl

    //todo database
}