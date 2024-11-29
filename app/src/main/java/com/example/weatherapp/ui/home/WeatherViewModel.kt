package com.example.weatherapp.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.weatherapp.network.Result
import com.example.weatherapp.network.repository.WeatherForecastService
import com.example.weatherapp.network.repository.WeatherReportService
import com.example.weatherapp.network.responseClass.ForecastResponse
import com.example.weatherapp.network.responseClass.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject



    @HiltViewModel
    class WeatherViewModel @Inject constructor(
        private val weatherReportService: WeatherReportService,
        private val forecastService: WeatherForecastService
    ) : ViewModel() {

        private val _weatherData = MutableStateFlow<Result<WeatherResponse>>(Result.Loading)
        val weatherData: StateFlow<Result<WeatherResponse>> get() = _weatherData

        private val _forecastData = MutableStateFlow<Result<ForecastResponse>>(Result.Loading)
        val forecastData: StateFlow<Result<ForecastResponse>> get() = _forecastData



        fun fetchWeatherData(lat: Double, lon: Double, apiKey: String) {
            viewModelScope.launch {
                _weatherData.value = Result.Loading
                try {
                    val result = weatherReportService.getWeatherData(lat, lon, apiKey)
                    _weatherData.value = result
                } catch (e: Exception) {
                    _weatherData.value = Result.Error(e)
                }
            }
        }



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