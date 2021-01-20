package com.example.porschehistory.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.porschehistory.data.relations.YearWithEvent

@Dao
interface YearDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //need to learn about it more
    suspend fun addYear(year: Year)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvent(event: Event)

    @Query("SELECT * FROM year_table ORDER BY year ASC") //ORDER BY year ASC
    fun readAllData(): LiveData<List<Year>>

    @Query("SELECT * FROM year_table WHERE id = :year")
    fun readCurrentYearEvents(year: String): LiveData<List<YearWithEvent>> //readCurrentYearEvents(year: String)
}