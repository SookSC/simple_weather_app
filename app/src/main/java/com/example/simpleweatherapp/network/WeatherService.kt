package com.example.simpleweatherapp.network

import com.example.simpleweatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface WeatherService {
    @GET("data/3.0/onecall")
    fun getWeatherForecast(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String
    ) : Call<WeatherResponse>
}