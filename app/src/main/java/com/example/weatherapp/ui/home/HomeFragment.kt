package com.example.weatherapp.ui.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.weatherapp.MainActivityViewModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.graphics.Color
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.utils.Utils

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val weatherViewModel:WeatherViewModel by viewModels()
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
        val backgroundDrawable = Utils.getTimePeriodStyle()
        binding?.weatherHomePageCompose?.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        binding?.weatherHomePageCompose?.setContent {
            WeatherScreen(backgroundDrawable,findNavController())
        }

        observeLocation()
    }

    private fun observeLocation() {
        mainActivityViewModel.locationData.observe(viewLifecycleOwner) { location ->
            val (latitude, longitude) = location
            Log.d("LocationRepository", "Received location: Lat=$latitude, Lon=$longitude")

            weatherViewModel.fetchWeatherData(latitude,longitude,"52b6bc7eef01ef8476e925e0cc91bc58")
            weatherViewModel.fetchForecastData(latitude,longitude,"52b6bc7eef01ef8476e925e0cc91bc58")

        }
    }


}
