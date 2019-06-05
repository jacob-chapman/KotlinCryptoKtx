package com.example.kotlincrypto_ktx.di

import android.content.Context
import com.example.data.di.NomicsDataModule
import com.example.kotlincrypto_ktx.viewmodel.DashboardViewModel
import com.example.kotlincrypto_ktx.viewmodel.PricesViewModel
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NomicsDataModule::class])
interface ApplicationComponent {

    val pricesViewModel: PricesViewModel
    val dashboardViewModel: DashboardViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }
}

