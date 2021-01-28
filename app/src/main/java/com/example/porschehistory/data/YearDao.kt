package com.example.porschehistory.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.porschehistory.data.relations.YearWithEvent

@Dao
interface YearDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addYear(year: Year)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvent(event: Event)

    @Query("SELECT * FROM year_table ORDER BY year ASC")
    fun readAllData(): LiveData<List<Year>>

    @Query("SELECT * FROM year_table WHERE year = :year")
    fun readCurrentYearEvents(year: Int): LiveData<List<YearWithEvent>> //LiveData<List<YearWithEvent>>
}