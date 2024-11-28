package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.utils.loaction.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val locationViewModel: LocationViewModel by viewModels()
    private val activityViewModel: MainActivityViewModel by viewModels()
    private var latitude: Double? = null
    private var longitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeLocation()
        checkPermissionsAndFetchLocation()

    }
    private fun checkPermissionsAndFetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("LocationRepository", "Permission already granted")
            locationViewModel.getLocation()
        } else {
            Log.d("LocationRepository", "Requesting location permission")
            ActivityCompat.requestPermissions(
                this,
                arrayOf( Manifest.permission.ACCESS_FINE_LOCATION),
                1000
            )
        }
    }
    private fun observeLocation() {
        locationViewModel.location.observe(this) { result ->
            result.onSuccess {
                val location = result.getOrNull()
                latitude = location?.latitude
                longitude = location?.longitude
                Log.d("LocationRepository", "Lat: $latitude, Lon: $longitude")
                if (latitude != null && longitude != null) {
                    activityViewModel.setLocation(latitude!!, longitude!!)
                }
            }

            result.onFailure {
                val exception = result.exceptionOrNull()
                Log.e("LocationRepository", "Error: ${exception?.message}")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("LocationRepository", "Location permission granted")
                locationViewModel.getLocation()
            } else {
                Log.d("LocationRepository", "Location permission denied")
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}