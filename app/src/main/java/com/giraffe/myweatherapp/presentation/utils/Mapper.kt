package com.giraffe.myweatherapp.presentation.utils

import com.giraffe.myweatherapp.domain.entity.DailyTemperatureEntity
import com.giraffe.myweatherapp.domain.entity.HourlyTemperatureEntity
import com.giraffe.myweatherapp.presentation.model.DayUiModel
import com.giraffe.myweatherapp.presentation.model.HourUiModel


fun HourlyTemperatureEntity.toUi(): HourUiModel {
    return HourUiModel(
        iconRes = getWeatherIcon(code, isDayTime(time)),
        temperature = temperature,
        time = time.split("T").last()
    )
}

fun DailyTemperatureEntity.toUi(): DayUiModel {
    return DayUiModel(
        dayName = getDayNameFromDate(date),
        iconRes = getWeatherIcon(code),
        highTemperature = highTemperature,
        lowTemperature = lowTemperature
    )
}