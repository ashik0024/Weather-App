package com.example.weatherapp.ui.home

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
import com.example.weatherapp.R
import com.example.weatherapp.network.Result
import com.example.weatherapp.network.responseClass.WeatherResponse

@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val weatherState by weatherViewModel.weatherData.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            painter = painterResource(id = R.drawable.img_sunny),
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

                    TopSection(weather)

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 32.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        CurrentWeatherCard(weather)

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
