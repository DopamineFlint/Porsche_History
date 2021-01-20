package com.example.porschehistory.data

import androidx.lifecycle.LiveData
import com.example.porschehistory.data.relations.YearWithEvent

class YearRepository(private val yearDao: YearDao) {

    val readAllData: LiveData<List<Year>> = yearDao.readAllData()
    val readCurrentYearEvents: LiveData<List<YearWithEvent>> = yearDao.readCurrentYearEvents()

    suspend fun addYear(year: Year) {
        yearDao.addYear(year)
    }

}