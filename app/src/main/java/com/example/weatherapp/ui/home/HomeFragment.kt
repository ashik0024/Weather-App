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
        val backgroundDrawable = getTimePeriodStyle()
        binding?.weatherHomePageCompose?.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        binding?.weatherHomePageCompose?.setContent {
            WeatherScreen(backgroundDrawable)
        }

        observeLocation()
    }

    private fun observeLocation() {
        mainActivityViewModel.locationData.observe(viewLifecycleOwner) { location ->
            val (latitude, longitude) = location
            Log.d("LocationRepository", "Received location: Lat=$latitude, Lon=$longitude")

            weatherViewModel.fetchWeatherData(latitude,longitude,"64ff98f1b20306789b31e70080328a26")
            weatherViewModel.fetchForecastData(latitude,longitude,"64ff98f1b20306789b31e70080328a26")

        }
    }

    fun getTimePeriodStyle(): TimePeriodStyle {
        val currentTime = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)

        return when (currentTime) {
            in 0..3 -> TimePeriodStyle(
                drawable = R.drawable.midnight_img,
                backgroundColor = Color(0xFF9090AC),
                textColor = Color(0xFF484A82)
            )
            in 4..6 -> TimePeriodStyle(
                drawable = R.drawable.early_morning,
                backgroundColor = Color(0xFF5A8BAB),
                textColor = Color(0xFFAED5E4)
            )
            in 7..11 -> TimePeriodStyle(
                drawable = R.drawable.morning_img,
                backgroundColor = Color(0xFF71A78F),
                textColor = Color(0xFF71A78F)
            )
            in 12..15 -> TimePeriodStyle(
                drawable = R.drawable.afternoon_img,
                backgroundColor = Color(0xFFEFAA82),
                textColor = Color(0xFFEFAA82)
            )
            in 16..19 -> TimePeriodStyle(
                drawable = R.drawable.twilight_img,
                backgroundColor = Color(0xFFAC736A),
                textColor = Color(0xFFF6C8A4)
            )
            in 20..23 -> TimePeriodStyle(
                drawable = R.drawable.night_img,
                backgroundColor = Color(0xFF40666A),
                textColor = Color(0xFFC9E8E0)
            )
            else -> TimePeriodStyle(
                drawable = R.drawable.morning_img,
                backgroundColor = Color(0xFF71A78F),
                textColor = Color(0xFF71A78F)
            )
        }
    }

}
