package com.example.ece489acompanionapp.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is settings Fragment"
    }
    val text: LiveData<String> = _text

    private val _waterTrackerEnabled = MutableLiveData<Boolean>(true)
    val waterTrackerEnabled: LiveData<Boolean> = _waterTrackerEnabled

    private val _sleepTrackerEnabled = MutableLiveData<Boolean>(true)
    val sleepTrackerEnabled: LiveData<Boolean> = _sleepTrackerEnabled

    private val _meditationTrackerEnabled = MutableLiveData<Boolean>(true)
    val meditationTrackerEnabled: LiveData<Boolean> = _meditationTrackerEnabled

    private val _exerciseTrackerEnabled = MutableLiveData<Boolean>(true)
    val exerciseTrackerEnabled: LiveData<Boolean> = _exerciseTrackerEnabled

    private val _foodTrackerEnabled = MutableLiveData<Boolean>(true)
    val foodTrackerEnabled: LiveData<Boolean> = _foodTrackerEnabled

    private val _substanceAbuseTrackerEnabled = MutableLiveData<Boolean>(true)
    val substanceAbuseTrackerEnabled: LiveData<Boolean> = _substanceAbuseTrackerEnabled

    fun setWaterTrackerEnabled(waterTrackerEnabled: Boolean) {
        _waterTrackerEnabled.value = waterTrackerEnabled
    }

    fun getWaterTrackerEnabled(): Boolean? {
        return _waterTrackerEnabled.value
    }

    fun setSleepTrackerEnabled(sleepTrackerEnabled: Boolean) {
        _sleepTrackerEnabled.value = sleepTrackerEnabled
    }

    fun getSleepTrackerEnabled(): Boolean? {
        return _waterTrackerEnabled.value
    }

    fun setMeditationTrackerEnabled(meditationTrackerEnabled: Boolean) {
        _meditationTrackerEnabled.value = meditationTrackerEnabled
    }

    fun getMeditationTrackerEnabled(): Boolean? {
        return _meditationTrackerEnabled.value
    }

    fun setExerciseTrackerEnabled(exerciseTrackerEnabled: Boolean) {
        _exerciseTrackerEnabled.value = exerciseTrackerEnabled
    }

    fun getExerciseTrackerEnabled(): Boolean? {
        return _exerciseTrackerEnabled.value
    }

    fun setFoodTrackerEnabled(foodTrackerEnabled: Boolean) {
        _foodTrackerEnabled.value = foodTrackerEnabled
    }

    fun getFoodTrackerEnabled(): Boolean? {
        return _foodTrackerEnabled.value
    }

    fun setSubstanceAbuseTrackerEnabled(substanceAbuseTrackerEnabled: Boolean) {
        _substanceAbuseTrackerEnabled.value = substanceAbuseTrackerEnabled
    }

    fun getSubstanceAbuseTrackerEnabled(): Boolean? {
        return _substanceAbuseTrackerEnabled.value
    }

}