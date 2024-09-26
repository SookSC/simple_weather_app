package com.example.simpleweatherapp.network

import com.example.simpleweatherapp.model.LocationResponse
import com.example.simpleweatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {
    private val weatherService = RetrofitInstance.weatherService
    private val locationService = RetrofitInstance.locationService

    fun getLatitudeAndLongitude(city: String, apiKey: String, callback: (LocationResponse?) -> Unit) {
        locationService.getLatAndLongFromCityName(city, apiKey).enqueue(object : Callback<LocationResponse> {
            override fun onResponse(
                call: Call<LocationResponse>,
                response: Response<LocationResponse>
            ) {
                if(response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                callback(null)
            }
        })
    }


    fun getWeather(lat: Float, lon: Float, apiKey: String, callback: (WeatherResponse?) -> Unit) {
        weatherService.getWeatherForecast(lat, lon, apiKey).enqueue(object: Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}