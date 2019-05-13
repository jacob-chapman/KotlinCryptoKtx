package com.example.kotlincrypto_ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.example.kotlincrypto_ktx.fragment.PricesFragment
import com.example.kotlincrypto_ktx.viewmodel.CurrenciesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lifecycleScope
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, PricesFragment.newInstance(), "prices")
                .commit()
        }
    }

}
