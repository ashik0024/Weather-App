package com.example.weatherapp.network.responseClass


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class WeatherResponse(
    @SerialName("base")
    var base: String? = "",
    @SerialName("clouds")
    var clouds: Clouds? = Clouds(),
    @SerialName("cod")
    var cod: Int? = 0,
    @SerialName("coord")
    var coord: Coord? = Coord(),
    @SerialName("dt")
    var dt: Int? = 0,
    @SerialName("id")
    var id: Int? = 0,
    @SerialName("main")
    var main: Main? = Main(),
    @SerialName("name")
    var name: String? = "",
    @SerialName("sys")
    var sys: Sys? = Sys(),
    @SerialName("timezone")
    var timezone: Int? = 0,
    @SerialName("visibility")
    var visibility: Int? = 0,
    @SerialName("weather")
    var weather: List<Weather?>? = listOf(),
    @SerialName("wind")
    var wind: Wind? = Wind()
) : Parcelable {
    @Serializable
    @Parcelize
    data class Clouds(
        @SerialName("all")
        var all: Int? = 0
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Coord(
        @SerialName("lat")
        var lat: Double? = 0.0,
        @SerialName("lon")
        var lon: Double? = 0.0
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Main(
        @SerialName("feels_like")
        var feelsLike: Double? = 0.0,
        @SerialName("grnd_level")
        var grndLevel: Int? = 0,
        @SerialName("humidity")
        var humidity: Int? = 0,
        @SerialName("pressure")
        var pressure: Int? = 0,
        @SerialName("sea_level")
        var seaLevel: Int? = 0,
        @SerialName("temp")
        var temp: Double? = 0.0,
        @SerialName("temp_max")
        var tempMax: Double? = 0.0,
        @SerialName("temp_min")
        var tempMin: Double? = 0.0
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Sys(
        @SerialName("country")
        var country: String? = "",
        @SerialName("id")
        var id: Int? = 0,
        @SerialName("sunrise")
        var sunrise: Int? = 0,
        @SerialName("sunset")
        var sunset: Int? = 0,
        @SerialName("type")
        var type: Int? = 0
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Weather(
        @SerialName("description")
        var description: String? = "",
        @SerialName("icon")
        var icon: String? = "",
        @SerialName("id")
        var id: Int? = 0,
        @SerialName("main")
        var main: String? = ""
    ) : Parcelable

    @Serializable
    @Parcelize
    data class Wind(
        @SerialName("deg")
        var deg: Int? = 0,
        @SerialName("speed")
        var speed: Double? = 0.0
    ) : Parcelable
}