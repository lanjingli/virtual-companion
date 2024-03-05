package com.example.ece489acompanionapp.ui.companion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompanionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is companion Fragment"
    }
    val text: LiveData<String> = _text

    private val _headDecorator= MutableLiveData<String>().apply {
        value = "Default"
    }

    private val _faceDecorator= MutableLiveData<String>().apply {
        value = "Default"
    }

    fun saveHeadDecoratorSelected(position: String) {
        _headDecorator.value = position
    }

    fun getHeadDecoratorSelected(): String? {
        return _headDecorator.value
    }

    fun saveFaceDecoratorSelected(position: String) {
        _faceDecorator.value = position
    }

    fun getFaceDecoratorSelected(): String? {
        return _faceDecorator.value
    }
}