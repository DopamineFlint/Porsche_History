package com.example.porschehistory.data

import androidx.lifecycle.LiveData

class YearRepository(private val yearDao: YearDao) {

    val readAllData: LiveData<List<Year>> = yearDao.readAllData()

    suspend fun addYear(year: Year) {
        yearDao.addYear(year)
    }

}