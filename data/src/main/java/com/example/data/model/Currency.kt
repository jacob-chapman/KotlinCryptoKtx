package com.example.data.model

import com.squareup.moshi.Json

data class Currency (
    @field:Json(name="currency") val name: String,
    val price: String)