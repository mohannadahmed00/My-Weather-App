package com.giraffe.myweatherapp.presentation.model

import com.giraffe.myweatherapp.R

data class DetailUiModel(
    val iconRes: Int = R.drawable.fast_wind,
    val value: String = "13 KM/h",
    val label: String = "Wind",
)