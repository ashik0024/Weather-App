package com.example.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.network.responseClass.WeatherResponse

@Composable
fun TopSection(weather: WeatherResponse) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 48.dp
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_location_on_24),
                    contentDescription = "location",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = weather.name.toString()+", "+weather.sys?.country,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color =  Color.White
                )
            }


            Image(
                painter = painterResource(id = R.drawable.search_1),
                contentDescription = "search",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
        }


    }
}