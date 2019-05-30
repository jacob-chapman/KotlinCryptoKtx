package com.example.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.Dashboard

@Dao
interface DashboardDao {

    @Query("Select * from Dashboard where :currency = currency")
    fun getDashboardForCurrency(currency: String) : LiveData<Dashboard>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg dashboard: Dashboard)
}