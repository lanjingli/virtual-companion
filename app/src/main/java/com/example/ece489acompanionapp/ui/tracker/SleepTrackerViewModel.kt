package com.example.ece489acompanionapp.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SleepTrackerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sleep tracker Fragment"
    }
    val text: LiveData<String> = _text
}