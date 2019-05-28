package com.example.kotlincrypto_ktx

import android.app.Application
import com.example.kotlincrypto_ktx.di.ApplicationComponent
import com.example.kotlincrypto_ktx.di.ComponentProvider
import com.example.kotlincrypto_ktx.di.DaggerApplicationComponent

class KotlinCryptoKtxApp : Application(), ComponentProvider {
    override val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}
