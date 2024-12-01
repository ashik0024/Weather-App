package com.example.weatherapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.network.responseClass.WeatherResponse
import com.example.weatherapp.utils.Utils


@Composable
fun WeatherDetails(weather: WeatherResponse) {

Column(
    modifier = Modifier.fillMaxWidth()
) {
    SunRiseSunSet(weather)
    HumidityPressure(weather)
}

}
@Composable
fun SunRiseSunSet(weather: WeatherResponse) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
        ,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        WeatherDetailsCard(
            imageResId = R.drawable.sun_rise,
            name = "Sun Rise",
            description = Utils.convertUnixToTimeAmPm(weather.sys?.sunrise?.toLong()?:0)
        )

        WeatherDetailsCard(
            imageResId = R.drawable.sun_set,
            name = "Sun Set",
            description = Utils.convertUnixToTimeAmPm(weather.sys?.sunset?.toLong()?:0)
        )
    }
}
@Composable
fun HumidityPressure(weather: WeatherResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        WeatherDetailsCard(
            imageResId = R.drawable.pressure,
            name = "Pressure",
            description = String.format("%.1f%%", weather.main?.humidity?.toFloat())
        )


        WeatherDetailsCard(
            imageResId = R.drawable.humidity,
            name = "Humidity",
            description = "${weather.main?.pressure} hPa"
        )
    }
}

@Composable
fun WeatherDetailsCard(
    imageResId: Int,
    name: String,
    description: String
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .border(1.dp, color = Color(0xFF48319D), RoundedCornerShape(48.dp))
            .padding(12.dp)
            .size(width = 130.dp, height =40.dp )
            .background(
                shape = RoundedCornerShape(48.dp),
                color = Color.Transparent

            )

    ) {

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "type picture",
            modifier = Modifier
                .size(36.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Text(
                text = name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,

            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color.White,

            )
        }
    }
}






