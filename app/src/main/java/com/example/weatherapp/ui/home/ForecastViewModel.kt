package com.example.weatherapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.Result
import com.example.weatherapp.network.repository.WeatherForecastService
import com.example.weatherapp.network.responseClass.ForecastResponse
import com.example.weatherapp.network.responseClass.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastService: WeatherForecastService
) : ViewModel() {


    private val _forecastData = MutableStateFlow<Result<ForecastResponse>>(Result.Loading)
    val forecastData: StateFlow<Result<ForecastResponse>> get() = _forecastData

    fun fetchForecastData(lat: Double, lon: Double, apiKey: String) {
        viewModelScope.launch {
            _forecastData.value = Result.Loading
            try {
                val result = forecastService.getWeatherForecastData(lat, lon, apiKey)
                _forecastData.value = result
            } catch (e: Exception) {
                _forecastData.value = Result.Error(e)
            }
        }
    }

}