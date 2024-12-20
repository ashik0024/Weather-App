package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.search.ZilaInfo
import com.example.weatherapp.utils.loaction.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.weatherapp.utils.Utils
import com.google.gson.Gson


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
        window.statusBarColor = ContextCompat.getColor(this, R.color.statusBar)
        observeLocation()
        checkPermissionsAndFetchLocation()
    }
    private fun checkPermissionsAndFetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationViewModel.getLocation()
        } else {
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
                if (latitude != null && longitude != null) {
                    activityViewModel.setLocation(latitude!!, longitude!!)
                }
            }

            result.onFailure {
                val exception = result.exceptionOrNull()
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

                activityViewModel.setPermissionDenied(false)
                locationViewModel.getLocation()
            } else {

                activityViewModel.setPermissionDenied(true)
            }
        }
    }

}