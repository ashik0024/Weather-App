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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.network.responseClass.ForecastResponse
import com.example.weatherapp.utils.Utils


@Composable
fun ForecastWeatherCard(forecast: ForecastResponse) {

    val sf_pro = FontFamily(
        Font(R.font.sf_pro_regular, FontWeight.Normal),
        Font(R.font.sf_pro_regular, FontWeight.Bold)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(

            text = LocalContext.current.getString(R.string.forecast),
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            fontFamily = sf_pro
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
                        color = Color.White,
                        fontFamily = sf_pro
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
                        color = Color.White,
                        fontFamily = sf_pro
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}



