package com.example.weatherapp.ui.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.MainActivityViewModel
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.network.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val weatherViewModel:WeatherViewModel by viewModels()
    private val forecastViewModel:ForecastViewModel by viewModels()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeLocation()
        observeWeatherData()
        observeForecastData()
    }

    private fun observeLocation() {
        mainActivityViewModel.locationData.observe(viewLifecycleOwner) { location ->
            val (latitude, longitude) = location
            Log.d("LocationRepository", "Received location: Lat=$latitude, Lon=$longitude")

            weatherViewModel.fetchWeatherData(latitude,longitude,"64ff98f1b20306789b31e70080328a26")
            forecastViewModel.fetchForecastData(latitude,longitude,"64ff98f1b20306789b31e70080328a26")

        }
    }


    private fun observeWeatherData() {

        weatherViewModel.weatherData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("LocationRepository", ": "+result.data)
                }
                is Result.Error -> {
                    val error = result.exception
                    Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
                is Result.Loading -> {
                    Log.d("LocationRepository", ": loading")
                }

                else -> {}
            }
        })
    }
    private fun observeForecastData() {
        forecastViewModel.forecastData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("LocationRepository", ": "+result.data)
                }
                is Result.Error -> {
                    val error = result.exception
                    Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
                is Result.Loading -> {
                    Log.d("LocationRepository", ": loading")
                }

                else -> {}
            }
        })
    }

}
