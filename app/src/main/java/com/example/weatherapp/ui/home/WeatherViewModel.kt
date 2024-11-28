package com.example.weatherapp.ui.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.weatherapp.network.Result
import com.example.weatherapp.network.repository.WeatherReportService
import com.example.weatherapp.network.responseClass.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject



    @HiltViewModel
    class WeatherViewModel @Inject constructor(
        private val weatherReportService: WeatherReportService,
    ) : ViewModel() {

     private val _weatherData = MutableLiveData<Result<WeatherResponse>>()
     val weatherData: MutableLiveData<Result<WeatherResponse>> get() = _weatherData

    fun fetchWeatherData(lat:Double,lon:Double,apiKey:String) {
        viewModelScope.launch {

            _weatherData.value = Result.Loading
            val result = weatherReportService.getWeatherData(lat,lon,apiKey)
            weatherData.value = result
        }
    }

}