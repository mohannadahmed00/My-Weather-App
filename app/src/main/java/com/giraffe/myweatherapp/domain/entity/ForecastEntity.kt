package com.giraffe.myweatherapp.domain.entity

data class ForecastEntity(
    val isDay: Boolean,
    val currentWeatherCode: Int,
    val currentTemperature: Double,
    val currentHighTemperature: Double,
    val currentLowTemperature: Double,
    val windSpeed: Double,
    val humidity: Int,
    val rain: Double,
    val uvIndex: Double,
    val pressure: Double,
    val feelsLike: Double,
    val hourlyTemperatures: List<HourlyTemperatureEntity>,
    val dailyTemperatures: List<DailyTemperatureEntity>
)




