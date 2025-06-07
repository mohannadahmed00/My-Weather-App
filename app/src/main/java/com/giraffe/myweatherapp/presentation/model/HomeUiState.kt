package com.giraffe.myweatherapp.presentation.model

import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.domain.exception.DomainError

data class HomeUiState(
    val locationName: String = "Baghdad",
    val currentWeatherIcon: Int = R.drawable.day_mainly_clear,
    val currentTemperature: Double = 24.0,
    val currentWeatherDescription: String = "Partly cloudy",
    val currentHighTemperature: Double = 32.0,
    val currentLowTemperature: Double = 20.0,
    val windSpeed: Double = 13.0,
    val humidity: Int = 24,
    val rain: Double = 2.0,
    val uv: Double = 2.0,
    val pressure: Double = 1012.0,
    val feelsLike: Double = 22.0,
    val hourlyTemperatures: List<HourUiModel> = (0..23).map { HourUiModel() },
    val dailyTemperatures: List<DayUiModel> = (0..6).map { DayUiModel() },
    val error: DomainError? = null,
)