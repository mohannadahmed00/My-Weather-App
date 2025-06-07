package com.giraffe.myweatherapp.domain.entity

data class HourlyTemperatureEntity(
    val code: Int,
    val temperature: Double,
    val time: String,
)