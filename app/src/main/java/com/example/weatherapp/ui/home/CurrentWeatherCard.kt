package com.example.weatherapp.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.network.responseClass.WeatherResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun CurrentWeatherCard(weather: WeatherResponse) {
    var temperature=kelvinToCelsius(weather.main?.temp?:0.0)
    var humidity=String.format("%.1f%%", weather.main?.humidity?.toFloat())
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        backgroundColor = Color(0xFFB2DFDB)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Today", fontSize = 20.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))

            AsyncImage(
                model = "https://openweathermap.org/img/wn/${weather.weather?.get(0)?.icon}@2x.png",
                contentDescription = "image from api",
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.errorimg),
                fallback = painterResource(id = R.drawable.placeholder),
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "$temperature\u00B0C",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = weather.weather?.joinToString(", ") { it?.main ?: "Unknown" }.toString(),
                fontSize = 20.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pressure: ${weather.main?.pressure} hPa",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "Humidity: $humidity",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = convertUnixToDate(weather.dt?.toLong()?:0),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sunrise: ${convertUnixToTime(weather.sys?.sunrise?.toLong() ?: 0)}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = "Sunset: ${convertUnixToTime(weather.sys?.sunset?.toLong() ?: 0)}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

        }
    }
}
@SuppressLint("DefaultLocale")
fun kelvinToCelsius(kelvin: Double): String {
    val celsius = kelvin - 273.15
    return String.format("%.2f", celsius)
}
fun convertUnixToTime(unixTimestamp: Long): String {
    val date = Date(unixTimestamp * 1000)
    val sdf = SimpleDateFormat("hh:mm:ss a", Locale.getDefault())
    val time = sdf.format(date)
    return time.toUpperCase(Locale.getDefault())
}
fun convertUnixToDate(unixTimestamp: Long): String {
    val date = Date(unixTimestamp * 1000)
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(date)
}