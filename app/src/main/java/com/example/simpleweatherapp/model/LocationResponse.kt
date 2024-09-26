package com.example.simpleweatherapp.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class LocationResponse(
    @SerializedName("lat") val latitude: Float,
    @SerializedName("lon") val longitude: Float
)