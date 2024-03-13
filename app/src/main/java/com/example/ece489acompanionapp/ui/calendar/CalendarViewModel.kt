package com.example.ece489acompanionapp.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CalendarViewModel : ViewModel() {

    private val _events = MutableLiveData<MutableMap<LocalDate, List<Event>>>().apply {
        value = mutableMapOf<LocalDate, List<Event>>()
    }

    fun setEvents(events: MutableMap<LocalDate, List<Event>>) {
        _events.value = events
    }

    fun getEvents(): MutableMap<LocalDate, List<Event>>? {
        return _events.value
    }

    private val _points = MutableLiveData<Int>(0)

    fun addPoints(pointsToAdd: Int) {
        _points.value = _points.value?.plus(pointsToAdd)
    }

    fun getPoints(): Int? {
        return _points.value
    }

}