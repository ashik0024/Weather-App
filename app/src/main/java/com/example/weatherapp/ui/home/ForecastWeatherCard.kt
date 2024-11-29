package com.example.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.network.responseClass.WeatherResponse

@Composable
fun ForecastWeatherCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFE0F7FA)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            repeat(6) { index ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = if (index == 0) "Now" else "${2 * index} AM", fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Image(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Cloudy Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "${20 + index}°", fontSize = 14.sp)
                }
            }
        }
    }
}