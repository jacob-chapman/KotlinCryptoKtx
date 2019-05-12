package com.example.kotlincrypto_ktx.model

import com.example.data.model.Currency
import java.text.NumberFormat
import java.util.*


data class CurrencyModel(
    val name: String,
    var price: String
) {
    companion object {
        fun get(currency: Currency) : CurrencyModel {
            return CurrencyModel(currency.name, currency.price)
        }
    }

    init {
        var format = NumberFormat.getCurrencyInstance(Locale.US)
        price = format.format(price.toFloat())
    }
}