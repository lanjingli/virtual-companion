package com.example.ece489acompanionapp.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log

class PersonalInfoViewModel : ViewModel() {
    private val fullName: MutableLiveData<String> = MutableLiveData()
    private val age: MutableLiveData<Int> = MutableLiveData()
    private val height: MutableLiveData<Double> = MutableLiveData()
    private val weight: MutableLiveData<Double> = MutableLiveData()
    private val gender: MutableLiveData<String> = MutableLiveData()

    fun setName(name: String){
        fullName.value = name
    }
    fun getName(): String?{
        return fullName.value
    }

    fun setGender(g: String){
        gender.value = g
    }
    fun getGender(): String?{
        return gender.value
    }

    fun setAge(a: Int){
        age.value = a
    }
    fun getAge(): Int?{
        return age.value
    }

    fun setHeight(h: Double){
        height.value = h
    }
    fun getHeight():Double?{
        return height.value
    }

    fun setWeight(w: Double){
        weight.value = w
    }
    fun getWeight(): Double?{
        return weight.value
    }
}