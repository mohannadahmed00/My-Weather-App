package com.giraffe.myweatherapp.presentation.model

import com.giraffe.myweatherapp.R

data class DayUiModel(
    val dayName: String = "Monday",
    val iconRes: Int = R.drawable.day_mainly_clear,
    val highTemperature: Double = 32.0,
    val lowTemperature: Double = 20.0,
)