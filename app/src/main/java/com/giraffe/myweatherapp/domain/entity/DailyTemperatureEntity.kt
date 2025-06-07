package com.giraffe.myweatherapp.domain.entity

data class DailyTemperatureEntity(
    val date: String,
    val code: Int,
    val highTemperature: Double,
    val lowTemperature: Double,
)