package com.giraffe.myweatherapp.presentation.model

import com.giraffe.myweatherapp.R
import com.giraffe.myweatherapp.domain.exception.DomainError

data class HomeUiState(
    val locationName: String = "---",
    val currentWeatherIcon: Int = R.drawable.day_mainly_clear,
    val currentTemperature: Double = 0.0,
    val currentWeatherDescription: String = "---",
    val currentHighTemperature: Double = 0.0,
    val currentLowTemperature: Double = 0.0,
    val windSpeed: Double = 0.0,
    val humidity: Int = 0,
    val rain: Double = 0.0,
    val uv: Double = 0.0,
    val pressure: Double = 0.0,
    val feelsLike: Double = 0.0,
    val hourlyTemperatures: List<HourUiModel> = (0..23).map { HourUiModel() },
    val dailyTemperatures: List<DayUiModel> = (0..6).map { DayUiModel() },
    val error: DomainError? = null,

    val isLocationPermissionDialogVisible: Boolean = false,
    val isLocationServiceDialogVisible: Boolean = false,
    val isLocationPermanentlyDeclined: Boolean = false,
)