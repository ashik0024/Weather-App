package com.example.weatherapp.utils.loaction

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationRepository(private val context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fun fetchLocation(): LiveData<Result<Location>> {
        val locationLiveData = MutableLiveData<Result<Location>>()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("LocationRepository", "Permission not granted")
            locationLiveData.value = Result.failure(Exception("Permission not granted"))
            return locationLiveData
        }else{
            Log.e("LocationRepository", "Permission  granted")
        }

        Log.d("LocationRepository", "Fetching last location")
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    Log.d("LocationRepository", "Location fetched: $location")
                    locationLiveData.value = Result.success(location)
                } else {
                    Log.d("LocationRepository", "Last location is null, requesting updates")
                    // Request new location updates if last location is null
                    val locationRequest = LocationRequest.create().apply {
                        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                        interval = 5000
                        fastestInterval = 2000
                    }

                    fusedLocationClient.requestLocationUpdates(
                        locationRequest,
                        object : LocationCallback() {
                            override fun onLocationResult(locationResult: LocationResult) {
                                val newLocation = locationResult.lastLocation
                                if (newLocation != null) {
                                    Log.d("LocationRepository", "New location fetched: $newLocation")
                                    locationLiveData.postValue(Result.success(newLocation))
                                    fusedLocationClient.removeLocationUpdates(this)
                                } else {
                                    Log.e("LocationRepository", "Failed to fetch new location")
                                }
                            }
                        },
                        Looper.getMainLooper()
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.e("LocationRepository", "Error fetching location: ${exception.message}")
                locationLiveData.value = Result.failure(exception)
            }

        return locationLiveData
    }

}

