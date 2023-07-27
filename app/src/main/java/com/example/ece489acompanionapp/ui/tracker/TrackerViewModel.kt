package com.example.ece489acompanionapp.ui.tracker

import android.widget.ImageButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ece489acompanionapp.R

class TrackerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tracker Fragment"
    }

    //private val _water =  MutableLiveData<MutableList<Boolean>>()
    private val _water: MutableLiveData<MutableList<Boolean>> by lazy {
        MutableLiveData<MutableList<Boolean>>().apply {
            value = MutableList(10) { true }
        }
    }

    private val _sleep: MutableLiveData<MutableList<Boolean>> by lazy {
        MutableLiveData<MutableList<Boolean>>().apply {
            value = MutableList(12) { true }
        }
    }

    private val _curWaterIntake = MutableLiveData<Int>(0)

    private val _curHoursSlept = MutableLiveData<Int>(0)

    val text: LiveData<String> = _text

    fun setWaterIsFull(ind: Int, isFull: Boolean) {
        //val cur = _water.value?.get(ind)
        _water.value?.set(ind, isFull)
    }

    fun getWaterState(ind:Int): Boolean? {
        return _water.value?.get(ind)
    }

    fun saveWaterIntake() {
        var count = 0
        _water.value?.forEach { intake->
            if (intake == true) {
                count++
            }
        }
        _curWaterIntake.value = count
    }

    fun getSleepState(ind:Int): Boolean? {
        return _sleep.value?.get(ind)
    }
    fun saveSleepIntake() {
        var count = 0
        _sleep.value?.forEach { intake->
            if (intake == true) {
                count++
            }
        }
        _curHoursSlept.value = count
    }

}

