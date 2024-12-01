package com.example.weatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R
import com.example.weatherapp.ui.home.TimePeriodStyle
import java.io.BufferedReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    @SuppressLint("DefaultLocale")
    fun kelvinToCelsius(kelvin: Double): String {
        val celsius = kelvin - 273.15
        return String.format("%.0f", celsius)
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
    fun convertUnixToTimeAmPm(unixTimestamp: Long): String {
        val date = Date(unixTimestamp * 1000)
        val sdf = SimpleDateFormat("hh a", Locale.getDefault())
        val time = sdf.format(date)
        return time.toUpperCase(Locale.getDefault())
    }

    fun readJsonFromRaw(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId).use { inputStream ->
            inputStream.bufferedReader().use { it.readText() }
        }
    }
//    fun getTimePeriodStyle(): TimePeriodStyle {
//        val currentTime = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
//
//        return when (currentTime) {
//            in 0..3 -> TimePeriodStyle(
//                drawable = R.drawable.midnight_img,
//                backgroundColor = Color(0xFF9090AC),
//                textColor = Color(0xFF484A82)
//            )
//            in 4..6 -> TimePeriodStyle(
//                drawable = R.drawable.early_morning,
//                backgroundColor = Color(0xFF5A8BAB),
//                textColor = Color(0xFFAED5E4)
//            )
//            in 7..11 -> TimePeriodStyle(
//                drawable = R.drawable.morning_img,
//                backgroundColor = Color(0xFF9FDCA8),
//                textColor = Color(0xFF71A78F)
//            )
//            in 12..15 -> TimePeriodStyle(
//                drawable = R.drawable.afternoon_img,
//                backgroundColor = Color(0xFFFAE2BD),
//                textColor = Color(0xFFEFAA82)
//            )
//            in 16..19 -> TimePeriodStyle(
//                drawable = R.drawable.twilight_img,
//                backgroundColor = Color(0xFFAC736A),
//                textColor = Color(0xFFF6C8A4)
//            )
//            in 20..23 -> TimePeriodStyle(
//                drawable = R.drawable.night_img,
//                backgroundColor = Color(0xFF40666A),
//                textColor = Color(0xFFC9E8E0)
//            )
//            else -> TimePeriodStyle(
//                drawable = R.drawable.morning_img,
//                backgroundColor = Color(0xFF9FDCA8),
//                textColor = Color(0xFF71A78F)
//            )
//        }
//    }
}