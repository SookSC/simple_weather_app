package com.example.simpleweatherapp.network
import com.example.simpleweatherapp.model.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface LocationService {
    @GET("geo/1.0/direct")
    fun getLatAndLongFromCityName(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
    ) : Call<LocationResponse>
}


//
//interface LocationServices {
//    @GET("/dan/0.1")
//    fun getLocationData(
//        @Query("lat") latitude: String,
//        @Query("lon") longitude: String
//    ) : Call<LocationResponse>
//}