package com.example.weatherapp.ui.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.network.Result
import com.example.weatherapp.network.responseClass.ForecastResponse
import com.example.weatherapp.network.responseClass.WeatherResponse
import com.example.weatherapp.network.retrofit.AppContext

@Composable
fun WeatherScreen(
    findNavController: NavController,
    permissionDenied: Boolean,
    weatherViewModel: WeatherViewModel = hiltViewModel()
    ) {
    val weatherState by weatherViewModel.weatherData.collectAsState()
    val forecastState by weatherViewModel.forecastData.collectAsState()
    val timePeriodStyle= TimePeriodStyle()
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(timePeriodStyle.drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (permissionDenied) {

                Toast.makeText(LocalContext.current, "Location permission denied. Please enable it to fetch weather data.", Toast.LENGTH_SHORT).show()

            }else{
                when (weatherState) {
                    is Result.Loading -> {
//
                        LoadingIndicator(true)
                        Log.d("WeatherScreen", "1: ")
                    }
                    is Result.Success -> {
                        val weather = (weatherState as Result.Success<WeatherResponse>).data
                        Log.d("getWeatherGroup", "$weather")
                        Log.d("WeatherScreen", "2: ")
                        TopSection(weather,
                            onSearchClicked = {
                                findNavController.navigate(R.id.home_to_search)
                            })

                        Column(
                            modifier = Modifier
                                .padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 4.dp )
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            CurrentWeatherCard(weather,timePeriodStyle)

                            when (forecastState) {
                                is Result.Loading -> {
                                    Log.d("WeatherScreen", "3: ")
                                }
                                is Result.Success -> {
                                    Log.d("WeatherScreen", "4: ")
                                    val forecast = (forecastState as Result.Success<ForecastResponse>).data
                                    WeatherDetails(weather)
                                    ForecastWeatherCard(forecast)

                                    LoadingIndicator(false)
                                }
                                is Result.Error -> {
                                    Log.d("WeatherScreen", "5: ")
                                    val errorMessage = (forecastState as Result.Error).exception.message
                                    LoadingIndicator(false)
                                    Toast.makeText(AppContext.getContext(),"Error: $errorMessage",Toast.LENGTH_LONG).show()
                                }
                            }
                        }

                    }
                    is Result.Error -> {
                        Log.d("WeatherScreen", "6: ")
                        val errorMessage = (weatherState as Result.Error).exception.message
                        LoadingIndicator(false)
                        Toast.makeText(AppContext.getContext(),"Error: $errorMessage",Toast.LENGTH_LONG).show()

                    }
                }
            }



        }
    }

}

@Composable
fun LoadingIndicator(isLoading: Boolean) {
    if (isLoading) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                strokeWidth = 6.dp
            )
        }
    }
}

