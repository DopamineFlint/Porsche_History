package com.example.porschehistory.data

import android.app.Application
import androidx.lifecycle.*
import com.example.porschehistory.data.relations.YearWithEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class YearViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Year>>

    private val repository: YearRepository
    private val mYear = MutableLiveData<Int>()

    init {
        val yearDao = YearDataBase.getDatabase(application).yearDao()
        repository = YearRepository(yearDao)
        readAllData = repository.readAllData
    }

    val eventsInCurrentYear: LiveData<List<YearWithEvent>> = mYear.switchMap {
        repository.readCurrentYearEvents(it)
    }

    fun addYear(year: Year) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addYear(year)
        }
    }

    fun addEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEvent(event)
        }
    }

    fun setCurrentYearEvents(year: Int) {
        mYear.value = year
    }

}