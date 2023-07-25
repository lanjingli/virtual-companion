package com.example.ece489acompanionapp.ui.companion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompanionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is companion Fragment"
    }
    val text: LiveData<String> = _text
}