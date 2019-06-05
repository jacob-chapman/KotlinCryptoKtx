package com.example.kotlincrypto_ktx.di

import com.example.newsfeature.di.NewsFeatureComponent

interface ComponentProvider {
    val applicationComponent: ApplicationComponent
    val newsFeatureComponent: NewsFeatureComponent
}