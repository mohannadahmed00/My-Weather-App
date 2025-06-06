package com.giraffe.myweatherapp.presentation.viewmodel

import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.presentation.model.DayUiModel
import com.giraffe.myweatherapp.presentation.model.HourUiModel

data class HomeUiState(
    val locationName: String = "Baghdad",
    val currentWeatherIcon: Int = R.drawable.day_mainly_clear,
    val currentTemperature: Int = 24,
    val currentWeatherDescription: String = "Partly cloudy",
    val currentHighTemperature: Int = 32,
    val currentLowTemperature: Int = 20,
    val windFast: Int = 13,
    val humidity: Int = 24,
    val rain: Int = 2,
    val uv: Int = 2,
    val pressure: Int = 1012,
    val feelsLike: Int = 22,
    val hourlyTemperatures: List<HourUiModel> = (0..23).map { HourUiModel() },
    val dailyTemperatures: List<DayUiModel> = (0..6).map { DayUiModel() },
)
