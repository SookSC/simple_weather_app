package com.example.simpleweatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapp.model.DailyData

class WeatherAdapter(private var weatherData: List<DailyData>) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val humidityTextView = view.findViewById<TextView>(R.id.humidity_text_view)
        val windSpeedTextView = view.findViewById<TextView>(R.id.wind_speed_text_view)
        val minTextView = view.findViewById<TextView>(R.id.min_text_view)
        val maxTextView = view.findViewById<TextView>(R.id.max_text_view)
        val conditionTextView = view.findViewById<TextView>(R.id.condition_text_view)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = weatherData[position]
        holder.humidityTextView.text = data.humidity.toString()
        holder.windSpeedTextView.text = data.windSpeed.toString()
        holder.minTextView.text = data.temperature.min.toString()
        holder.maxTextView.text = data.temperature.max.toString()
        holder.conditionTextView.text = data.weather.condition
    }

    override fun getItemCount() = weatherData.size

    fun updateData(newWeatherData: List<DailyData>) {
        weatherData = newWeatherData
        notifyDataSetChanged()
    }
}