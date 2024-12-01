package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _locationData = MutableLiveData<Pair<Double, Double>>()
    val locationData: LiveData<Pair<Double, Double>> get() = _locationData

    private val _permissionDenied =  MutableLiveData<Boolean>(false)
    val permissionDenied:  LiveData<Boolean> = _permissionDenied

    fun setLocation(latitude: Double, longitude: Double) {
        _locationData.value = Pair(latitude, longitude)
    }

    fun setPermissionDenied(denied: Boolean) {
        _permissionDenied.value = denied
    }
}