package com.giraffe.myweatherapp.data.utils

import com.giraffe.myweatherapp.data.response.Daily
import com.giraffe.myweatherapp.data.response.ForecastResponse
import com.giraffe.myweatherapp.data.response.Hourly
import com.giraffe.myweatherapp.data.response.LocationResponse
import com.giraffe.myweatherapp.domain.entity.DailyTemperatureEntity
import com.giraffe.myweatherapp.domain.entity.ForecastEntity
import com.giraffe.myweatherapp.domain.entity.HourlyTemperatureEntity
import com.giraffe.myweatherapp.domain.entity.LocationEntity

fun LocationResponse.toEntity() = LocationEntity(
    locationName = locationName,
    latitude = latitude,
    longitude = longitude
)

fun ForecastResponse.toEntity(): ForecastEntity {
    return ForecastEntity(
        isDay = current.isDay == 1,
        currentWeatherCode = current.weatherCode,
        currentTemperature = current.temperature,
        currentHighTemperature = daily.highTemperatures.first(),
        currentLowTemperature = daily.lowTemperatures.first(),
        windSpeed = current.windSpeed,
        humidity = current.humidity,
        rain = current.rain,
        uvIndex = daily.uvIndices.first(),
        pressure = current.pressure,
        feelsLike = current.feelLikeTemperature,
        hourlyTemperatures = hourly.toEntities(),
        dailyTemperatures = daily.toEntities()
    )
}

fun Hourly.toEntities(): List<HourlyTemperatureEntity> {
    return temperatures.take(24).mapIndexed { index, temperature ->
        HourlyTemperatureEntity(
            code = weatherCodes[index],
            temperature = temperature,
            time = times[index]
        )
    }
}

fun Daily.toEntities(): List<DailyTemperatureEntity> {
    return weatherCodes.mapIndexed { index, code ->
        DailyTemperatureEntity(
            date = times[index],
            code = code,
            highTemperature = highTemperatures[index],
            lowTemperature = lowTemperatures[index]
        )
    }
}