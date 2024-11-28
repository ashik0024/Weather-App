package com.example.weatherapp.network.repository

import com.example.weatherapp.network.Result
import com.example.weatherapp.network.responseClass.ForecastResponse

import com.example.weatherapp.network.retrofit.ApiInterface
import javax.inject.Inject

class WeatherForecastService @Inject constructor(
    private val apiService: ApiInterface
) {
    suspend fun getWeatherForecastData(lat:Double,lon:Double,apiKey:String): Result<ForecastResponse> {
        return safeApiCall {
            val response = apiService.getWeatherForecast(lat,lon,apiKey)
            response
        }
    }
}