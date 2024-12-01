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
import com.example.weatherapp.ui.search.ZilaInfo
import com.example.weatherapp.utils.Utils

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val weatherViewModel:WeatherViewModel by viewModels()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private var lat:Double?=null
    private var long:Double?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<ZilaInfo>("zilaInfo")?.let { zilaInfo ->
            lat=zilaInfo.coord?.lat?:0.0
            long=zilaInfo.coord?.lon?:0.0
        }
        mainActivityViewModel.permissionDenied.observe(viewLifecycleOwner) { permissionDenied ->
            binding?.weatherHomePageCompose?.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            binding?.weatherHomePageCompose?.setContent {
                WeatherScreen(findNavController(),permissionDenied)
            }
        }

        observeLocation()
    }

    private fun observeLocation() {
        /**
         * when user arrives in home fragment from app lunch then take user current location observing data from
         * viewmodel. if user arrives from search page then take selected zilla's location data.
         */
        mainActivityViewModel.locationData.observe(viewLifecycleOwner) { location ->
            if (lat==null|| long==null)
            {
                lat=location.first
                long=location.second

            }
            weatherViewModel.fetchWeatherData(lat?:0.0,long?:0.0,getString(R.string.api_key))
            weatherViewModel.fetchForecastData(lat?:0.0,long?:0.0,getString(R.string.api_key))

        }
    }


}
