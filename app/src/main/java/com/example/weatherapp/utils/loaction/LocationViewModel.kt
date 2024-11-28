package com.example.weatherapp.utils.loaction

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {

    private val _location = MutableLiveData<Result<Location>>()
    val location: LiveData<Result<Location>> = _location

    fun getLocation() {
        repository.fetchLocation().observeForever { result ->
            _location.value = result
        }
    }
}


