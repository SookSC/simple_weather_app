package com.example.simpleweatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleweatherapp.viewmodels.HomeViewModel
import com.example.simpleweatherapp.BuildConfig
import com.example.simpleweatherapp.WeatherAdapter
import com.example.simpleweatherapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val apiKey = BuildConfig.OPENWEATHER_API_KEY

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weatherAdapter = WeatherAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = weatherAdapter

        // handle on button click
        binding.searchButtonView.setOnClickListener {
            viewModel.setCity(binding.cityInputTextView.text.toString())
            viewModel.fetchLocation(viewModel.cityInput.value!!, apiKey)

            viewModel.locationData.observe(viewLifecycleOwner) { locationResponse ->
                locationResponse?.let {
                    viewModel.fetchWeather(locationResponse.latitude, locationResponse.longitude, apiKey)
                }
            }

            viewModel.weatherData.observe(viewLifecycleOwner) { weatherResponse ->
                weatherResponse?.let {
                    updateWeatherUI(true)
                    weatherAdapter.updateData(listOf(weatherResponse.daily))
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun updateWeatherUI(showRecyclerView: Boolean) {
        if (showRecyclerView) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.errorTextView.visibility = View.GONE
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.errorTextView.visibility = View.VISIBLE
        }
    }
}