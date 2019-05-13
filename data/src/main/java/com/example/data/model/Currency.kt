package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Currency (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @field:Json(name="currency") val name: String,
    val price: String)