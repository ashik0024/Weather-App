package com.example.weatherapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.network.responseClass.WeatherResponse
import com.example.weatherapp.utils.Utils

@Composable
fun CurrentWeatherCard(weather: WeatherResponse, timePeriodStyle: TimePeriodStyle) {
    val sf_pro = FontFamily(
        Font(R.font.sf_pro_regular, FontWeight.Normal),
        Font(R.font.sf_pro_regular, FontWeight.Bold)
    )
    var temperature=Utils.kelvinToCelsius(weather.main?.temp?:0.0)
    Column(
        modifier = Modifier
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Today", fontSize = 20.sp, color = timePeriodStyle.textColor, fontFamily = sf_pro)
        Spacer(modifier = Modifier.height(8.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${weather.weather?.get(0)?.icon}@2x.png",
                contentDescription = "image from api",
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.errorimg),
                fallback = painterResource(id = R.drawable.placeholder),
                modifier = Modifier.size(60.dp),
                colorFilter = ColorFilter.tint(timePeriodStyle.textColor)
            )
            Text(
                text = "$temperature\u00B0C",
                fontSize = 48.sp,

                color = timePeriodStyle.textColor ,
                fontFamily = sf_pro
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = weather.weather?.joinToString(", ") { it?.main ?: "Unknown" }.toString(),
            fontSize = 20.sp,
            color = timePeriodStyle.textColor,
            fontFamily = sf_pro
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = Utils.convertUnixToDate(weather.dt?.toLong()?:0),
            fontSize = 14.sp,
            color = timePeriodStyle.textColor,
            fontFamily = sf_pro
        )

    }
}
