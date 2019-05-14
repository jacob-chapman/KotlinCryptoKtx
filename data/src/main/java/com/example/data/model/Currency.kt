package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Currency (
    @field:Json(name="currency") @PrimaryKey val name: String,
    val price: String)