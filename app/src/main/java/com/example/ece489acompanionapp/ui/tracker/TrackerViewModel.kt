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
            value = MutableList(10) { true }
        }
    }

    private val _exercise: MutableLiveData<MutableList<Boolean>> by lazy {
        MutableLiveData<MutableList<Boolean>>().apply {
            value = MutableList(10) { true }
        }
    }

    private val _meditation: MutableLiveData<MutableList<Boolean>> by lazy {
        MutableLiveData<MutableList<Boolean>>().apply {
            value = MutableList(10) { true }
        }
    }

    private val _food: MutableLiveData<MutableList<Boolean>> by lazy {
        MutableLiveData<MutableList<Boolean>>().apply {
            value = MutableList(10) { true }
        }
    }

    private val _curWaterIntake = MutableLiveData<Int>(0)

    private val _curHoursSlept = MutableLiveData<Int>(0)

    private val _curHoursExercised = MutableLiveData<Int>(0)

    private val _curHoursMeditated = MutableLiveData<Int>(0)

    private val _curFoodIntake = MutableLiveData<Int>(0)

    val text: LiveData<String> = _text

    private val _recomMet: MutableLiveData<MutableMap<String, Boolean>> by lazy {
        MutableLiveData<MutableMap<String, Boolean>>().apply {
            value = mutableMapOf(
                "water" to false,
                "sleep" to false,
                "meditation" to false,
                "exercise" to false,
                "food" to false,
                "subAbuse" to false
            )
        }
    }

    private val _totalPoints = MutableLiveData<Int>(0)

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

    fun setSleepIsFull(ind: Int, isFull: Boolean) {
        //val cur = _water.value?.get(ind)
        _sleep.value?.set(ind, isFull)
    }

    fun getSleepState(ind:Int): Boolean? {
        return _sleep.value?.get(ind)
    }
    fun saveHoursSlept() {
        var count = 0
        _sleep.value?.forEach { hour->
            if (hour == true) {
                count++
            }
        }
        _curHoursSlept.value = count
    }

    fun setExerciseIsFull(ind: Int, isFull: Boolean) {
        _exercise.value?.set(ind, isFull)
    }
    fun getExerciseState(ind:Int): Boolean? {
        return _exercise.value?.get(ind)
    }

    fun saveExerciseIntake() {
        var count = 0
        _exercise.value?.forEach { hour->
            if (hour == true) {
                count++
            }
        }
        _curHoursExercised.value = count
    }

    fun setMeditationIsFull(ind: Int, isFull: Boolean) {
        _meditation.value?.set(ind, isFull)
    }
    fun getMeditationState(ind:Int): Boolean? {
        return _meditation.value?.get(ind)
    }

    fun saveMeditationIntake() {
        var count = 0
        _meditation.value?.forEach { hour->
            if (hour == true) {
                count++
            }
        }
        _curHoursMeditated.value = count
    }

    fun setFoodIsFull(ind: Int, isFull: Boolean) {
        _food.value?.set(ind, isFull)
    }
    fun getFoodState(ind:Int): Boolean? {
        return _food.value?.get(ind)
    }

    fun saveFoodIntake() {
        var count = 0
        _food.value?.forEach { intake->
            if (intake == true) {
                count++
            }
        }
        _curFoodIntake.value = count
    }

    fun setRecomMet(tracker: String, isMet :Boolean) {
        _recomMet.value?.set(tracker, isMet)
    }

    fun getRecomMet(key: String): Boolean? {
        return _recomMet.value?.get(key)
    }

    fun getNumRecomMet(): Int? {
        var recMet = 0
        _recomMet.value?.forEach { (_, isMet) ->
            if (isMet) {
                recMet+=1
            }
        }
        return recMet
    }

    fun setTotalPoints(pointsToAdd: Int) {
        _totalPoints.value = _totalPoints.value?.plus(pointsToAdd)
    }

    fun getTotalPoints(): Int? {
        return _totalPoints.value
    }

}