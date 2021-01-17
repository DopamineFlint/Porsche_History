package com.example.porschehistory.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface YearDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //need to learn about it more
    suspend fun addYear(year: Year)

    @Query("SELECT * FROM year_table ORDER BY year ASC") //ORDER BY year ASC
    fun readAllData(): LiveData<List<Year>>

}