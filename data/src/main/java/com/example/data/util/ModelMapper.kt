package com.example.data.util

import com.example.data.model.Currency

class ModelMapper {
    companion object {
        fun mapToCurrency(dbCurrency: Currency) : com.example.domain.entity.Currency {
            return com.example.domain.entity.Currency(
                dbCurrency.name,
                dbCurrency.price
            )
        }
    }
}