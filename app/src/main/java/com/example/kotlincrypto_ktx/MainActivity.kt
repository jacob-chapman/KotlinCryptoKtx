package com.example.kotlincrypto_ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.observe
import com.example.kotlincrypto_ktx.viewmodel.CurrenciesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val currenciesViewModel: CurrenciesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currenciesViewModel.loadCurrencies().observe(this) {
            Log.d("logging something", it.count().toString())
        }
    }
}
