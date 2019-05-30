package com.example.kotlincrypto_ktx.di

import android.content.Context
import com.example.data.di.NomicsDataModule
import com.example.kotlincrypto_ktx.viewmodel.DashboardViewModel
import com.example.kotlincrypto_ktx.viewmodel.PricesViewModel
import com.example.kotlincrypto_ktx.viewmodel.USNewsViewModel
import com.example.news.di.NewsDataModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NomicsDataModule::class, NewsDataModule::class])
interface ApplicationComponent {

    val pricesViewModel: PricesViewModel
    val dashboardViewModel: DashboardViewModel
    val usNewsViewModel: USNewsViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }
}

interface ComponentProvider {
    val applicationComponent: ApplicationComponent
}