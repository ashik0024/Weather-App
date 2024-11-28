package com.example.weatherapp.network.repository

import com.example.weatherapp.network.Result
import com.example.weatherapp.network.responseClass.WeatherResponse
import com.example.weatherapp.network.retrofit.ApiInterface
import javax.inject.Inject


class WeatherReportService @Inject constructor(
    private val apiService: ApiInterface
) {
    suspend fun getWeatherData(lat:Double,lon:Double,apiKey:String): Result<WeatherResponse> {
        return safeApiCall {
            val response = apiService.getCurrentWeather(lat,lon,apiKey)
            response
        }
    }
}