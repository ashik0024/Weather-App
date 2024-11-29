package com.example.weatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Utils {
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
}