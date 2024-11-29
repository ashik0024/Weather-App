package com.example.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.network.responseClass.ForecastResponse
import com.example.weatherapp.utils.Utils


@Composable
fun ForecastWeatherCard(forecast: ForecastResponse, timePeriodStyle: TimePeriodStyle) {
    val utils = Utils()
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = timePeriodStyle.backgroundColor
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            horizontalArrangement = Arrangement.spacedBy(16.dp) // Space between items
        ) {
            items(forecast.list?.size?: 0) { index ->
                val forecastItem = forecast.list?.get(index)
                var temperature=utils.kelvinToCelsius(forecastItem?.main?.temp?:0.0)

                val imageUrl = "https://openweathermap.org/img/wn/${forecastItem?.weather?.get(0)?.icon}@2x.png"
                val painter = rememberAsyncImagePainter(imageUrl)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text =utils.convertUnixToTimeAmPm(forecastItem?.dt?.toLong()?:0),
                        fontSize = 14.sp,
                        color = timePeriodStyle.textColor)
                    Spacer(modifier = Modifier.height(4.dp))
                    Image(
                        painter = painter,
                        contentDescription = "Weather icon",
                        modifier = Modifier.size(40.dp),
                        alignment = Alignment.Center,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "$temperature\u00B0C",
                        fontSize = 14.sp,
                        color = timePeriodStyle.textColor)
                }
            }
        }
    }
}


