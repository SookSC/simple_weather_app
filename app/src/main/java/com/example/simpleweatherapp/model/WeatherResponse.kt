package com.example.simpleweatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("daily") val daily: DailyData
)

data class DailyData(
    @SerializedName("temp") val temperature: Temperature,
    @SerializedName("humidity") val humidity: Float,
    @SerializedName("wind_speed") val windSpeed: Float,
    @SerializedName("weather") val weather: Weather
)

data class Temperature(
    @SerializedName("min") val min: Float,
    @SerializedName("max") val max: Float
)

data class Weather(
    @SerializedName("main") val condition: String
)