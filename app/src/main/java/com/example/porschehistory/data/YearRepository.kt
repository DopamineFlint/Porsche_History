package com.example.porschehistory.data

import androidx.lifecycle.LiveData
import com.example.porschehistory.data.relations.YearWithEvent

class YearRepository(private val yearDao: YearDao) {

    val readAllData: LiveData<List<Year>> = yearDao.readAllData()
    val readCurrentYearEvents: LiveData<List<YearWithEvent>> = yearDao.readCurrentYearEvents(1054)

    suspend fun addYear(year: Year) {
        yearDao.addYear(year)
    }

    suspend fun addEvent(event: Event) {
        yearDao.addEvent(event)
    }

    fun readCurrentYearEvents(year: Int): LiveData<List<YearWithEvent>> { //!!!
        return yearDao.readCurrentYearEvents(year)
    }

}