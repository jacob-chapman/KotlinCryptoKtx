package com.example.newsfeature.di

import network.NetworkModule
import dagger.Component
import dagger.Module

@Component(modules = [NewsNetworkModule::class])
interface NewsFeatureComponent {

}

@Module
class NewsNetworkModule : NetworkModule("https://newsapi.org")