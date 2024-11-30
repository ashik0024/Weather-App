package com.example.weatherapp.ui.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class ZilaInfo(
    @SerialName("coord")
    var coord: Coord? = Coord(),
    @SerialName("country")
    var country: String? = "",
    @SerialName("id")
    var id: Int? = 0,
    @SerialName("name")
    var name: String? = "",
    @SerialName("state")
    var state: String? = ""
) : Parcelable {
    @Serializable
    @Parcelize
    data class Coord(
        @SerialName("lat")
        var lat: Double? = 0.0,
        @SerialName("lon")
        var lon: Double? = 0.0
    ) : Parcelable
}