package com.example.weatherapp.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.R
import com.example.weatherapp.network.Result
import com.example.weatherapp.network.responseClass.ForecastResponse
import com.example.weatherapp.network.responseClass.WeatherResponse
@Composable
fun WeatherScreen(
    timePeriodStyle: TimePeriodStyle,
    findNavController: NavController,
    weatherViewModel: WeatherViewModel = hiltViewModel()
    ) {
    val weatherState by weatherViewModel.weatherData.collectAsState()
    val forecastState by weatherViewModel.forecastData.collectAsState()

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
            when (weatherState) {
                is Result.Loading -> {
                    Text("Loading...", style = MaterialTheme.typography.h6)
                }
                is Result.Success -> {
                    val weather = (weatherState as Result.Success<WeatherResponse>).data
                    Log.d("getWeatherGroup", weather.weather?.get(0)?.id.toString())

                    TopSection(weather = weather,
                        onSearchClicked = {
                            findNavController.navigate(R.id.home_to_search)
                        })

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 32.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CurrentWeatherCard(weather,timePeriodStyle)

                        when (forecastState) {
                            is Result.Loading -> {
                                Text("Loading Forecast...", style = MaterialTheme.typography.h6)
                            }
                            is Result.Success -> {
                                val forecast = (forecastState as Result.Success<ForecastResponse>).data
                                ForecastWeatherCard(forecast,timePeriodStyle)
                            }
                            is Result.Error -> {
                                val errorMessage = (forecastState as Result.Error).exception.message
                                Text("Error: $errorMessage", style = MaterialTheme.typography.h6)
                            }
                        }
                    }

                }
                is Result.Error -> {
                    val errorMessage = (weatherState as Result.Error).exception.message
                    Text("Error: $errorMessage", style = MaterialTheme.typography.h6)
                }
            }


        }
    }

}

