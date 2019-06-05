package com.example.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.model.Currency

@Dao
interface PricesDao {

    @Query("Select * from Currency")
    fun getAllCurrenciesNonLiveData() : List<Currency>

    @Query("Select * from Currency")
    fun getAllCurrencies() : LiveData<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg currency: Currency)

    @Query("DELETE FROM Currency")
    fun deleteAll()
}