package com.example.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Dashboard(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val currency: String,
    val dayOpen: String?,
    val dayVolume: String?,
    val weekOpen: String?,
    val weekVolume: String?,
    val weekOpenVolume: String?,
    val monthOpen: String?,
    val monthVolume: String?,
    val monthOpenVolume: String?,
    val yearOpen: String?,
    val yearVolume: String?,
    val yearOpenVolume: String?,
    val close: String?,
    val high: String?,
    val highTimestamp: String?,
    val highExchange: String?,
    val highQuoteCurrency: String?,
    val availableSupply: String?,
    val maxSupply: String?
) {
}