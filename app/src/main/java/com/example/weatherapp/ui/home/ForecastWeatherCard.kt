package com.example.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.network.responseClass.ForecastResponse
import com.example.weatherapp.utils.Utils


@Composable
fun ForecastWeatherCard(forecast: ForecastResponse) {
    val timePeriodStyle = TimePeriodStyle()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Forecast",
            fontSize = 20.sp,
            color = timePeriodStyle.textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(forecast.list?.size ?: 0) { index ->
                val forecastItem = forecast.list?.get(index)
                val temperature = Utils.kelvinToCelsius(forecastItem?.main?.temp ?: 0.0)
                val imageUrl = "https://openweathermap.org/img/wn/${forecastItem?.weather?.get(0)?.icon}@2x.png"
                val painter = rememberAsyncImagePainter(imageUrl)

                Column(
                    modifier = Modifier
                        .border(1.dp, color = Color(0xFF48319D), RoundedCornerShape(48.dp))
                        .shadow(10.dp, RoundedCornerShape(48.dp))
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(48.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = Utils.convertUnixToTimeAmPm(forecastItem?.dt?.toLong() ?: 0),
                        fontSize = 14.sp,
                        color = timePeriodStyle.textColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painter,
                        contentDescription = "Weather icon",
                        modifier = Modifier.size(40.dp),
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$temperature\u00B0C",
                        fontSize = 14.sp,
                        color = timePeriodStyle.textColor
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}



