package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.DashboardDao
import com.example.data.dao.PricesDao
import com.example.data.model.Currency
import com.example.data.model.Dashboard

@Database(
    entities = [Currency::class, Dashboard::class],
    version = 1)
abstract class NomicsDb : RoomDatabase(){

    abstract fun pricesDao() : PricesDao
    abstract fun dashboardDao() : DashboardDao

    companion object {
        private var instance : NomicsDb? = null
        private val lock = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(lock){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            NomicsDb::class.java, "nomics.db")
            .build()
    }
}