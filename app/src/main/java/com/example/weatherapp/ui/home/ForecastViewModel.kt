package com.example.weatherapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.Result
import com.example.weatherapp.network.repository.WeatherForecastService
import com.example.weatherapp.network.responseClass.ForecastResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastService: WeatherForecastService
) : ViewModel() {

    private val _forecastData = MutableLiveData<Result<ForecastResponse>>()
    val forecastData: MutableLiveData<Result<ForecastResponse>> get() = _forecastData

    fun fetchForecastData(lat:Double,lon:Double,apiKey:String) {
        viewModelScope.launch {

            _forecastData.value = Result.Loading
            val result = forecastService.getWeatherForecastData(lat,lon,apiKey)
            forecastData.value = result
        }
    }

}