package com.example.kotlincrypto_ktx

import android.app.Application
import com.example.data.di.DataModule
import com.example.domain.usecase.GetPricesLiveDataUseCase
import com.example.domain.usecase.GetPricesNonLiveDataUseCase
import com.example.kotlincrypto_ktx.viewmodel.CurrenciesNonLiveViewModel
import com.example.kotlincrypto_ktx.viewmodel.CurrenciesViewModel
import com.example.kotlincrypto_ktx.viewmodel.DashboardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KotlinCryptoKtxApp : Application() {

    private val module = module {
        viewModel { CurrenciesViewModel(get()) }
        viewModel { CurrenciesNonLiveViewModel(get()) }
        viewModel { DashboardViewModel(get()) }
        factory { GetPricesLiveDataUseCase(get()) }
        factory { GetPricesNonLiveDataUseCase(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KotlinCryptoKtxApp)
            modules(DataModule.module, module)
        }
    }
}
