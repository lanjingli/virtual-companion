package com.example.ece489acompanionapp.ui.companion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HeadDecoratorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is substance abuse tracker Fragment"
    }
    val text: LiveData<String> = _text
}