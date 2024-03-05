package com.example.ece489acompanionapp.ui.companion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FaceDecoratorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is face decorator Fragment"
    }
    val text: LiveData<String> = _text
}