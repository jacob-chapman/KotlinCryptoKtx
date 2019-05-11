package com.example.kotlincrypto_ktx

import android.app.Application
import com.example.data.di.DataModule
import com.example.kotlincrypto_ktx.di.AppModule
import com.example.kotlincrypto_ktx.viewmodel.CurrenciesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KotlinCryptoKtxApp : Application() {

    val module = module {
        viewModel { CurrenciesViewModel(get()) }
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