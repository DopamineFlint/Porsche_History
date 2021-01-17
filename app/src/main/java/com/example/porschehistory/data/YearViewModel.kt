package com.example.porschehistory.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YearViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Year>>
    private val repository: YearRepository

    init {
        val yearDao = YearDataBase.getDatabase(application).yearDao()
        repository = YearRepository(yearDao)
        readAllData = repository.readAllData
    }

    fun addYear(year: Year) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addYear(year)
        }
    }

}