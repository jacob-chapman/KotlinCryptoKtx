package com.example.kotlincrypto_ktx

import android.app.Application
import com.example.kotlincrypto_ktx.di.ApplicationComponent
import com.example.kotlincrypto_ktx.di.ComponentProvider
import com.example.kotlincrypto_ktx.di.DaggerApplicationComponent
import com.example.newsfeature.data.api.NewsInterceptor
import com.example.newsfeature.di.DaggerNewsFeatureComponent
import com.example.newsfeature.di.NewsFeatureComponent
import dagger.Component
import network.NetworkModule

class KotlinCryptoKtxApp : Application(), ComponentProvider, com.example.android.ComponentProvider {

    override val components: List<Any> by lazy { listOf(applicationComponent, newsFeatureComponent) }

    override val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override val newsFeatureComponent: NewsFeatureComponent by lazy {
        DaggerNewsFeatureComponent.builder().networkModule(NetworkModule("https://newsapi.org", NewsInterceptor())).build()
    }
}
