package com.example.simpleweatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapp.model.LocationResponse
import com.example.simpleweatherapp.model.WeatherResponse
import com.example.simpleweatherapp.network.WeatherRepository

class HomeViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _cityInput = MutableLiveData<String>()
    val cityInput: LiveData<String> get() = _cityInput

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> get() = _weatherData

    private val _locationData = MutableLiveData<LocationResponse?>()
    val locationData: LiveData<LocationResponse?> get() = _locationData

    fun setCity(city: String) {
        _cityInput.value = city
    }

    fun fetchLocation(city: String, apiKey: String) {
        repository.getLatitudeAndLongitude(city, apiKey) {
            _locationData.value = it
        }
    }

    fun fetchWeather(lat: Float, lon: Float, apiKey: String) {
        repository.getWeather(lat, lon, apiKey) {
            _weatherData.value = it
        }
    }
}