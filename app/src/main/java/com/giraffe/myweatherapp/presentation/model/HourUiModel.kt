package com.giraffe.myweatherapp.presentation.model

import com.giraffe.myweatherapp.R

data class HourUiModel(
    val iconRes: Int = R.drawable.day_mainly_clear,
    val temperature: Double = 25.0,
    val time: String = "11:00",
)