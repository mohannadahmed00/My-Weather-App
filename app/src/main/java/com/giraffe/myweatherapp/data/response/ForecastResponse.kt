package com.giraffe.myweatherapp.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val current: Current,
    @SerialName("current_units")
    val currentUnits: CurrentUnits,
    val daily: Daily,
    @SerialName("daily_units")
    val dailyUnits: DailyUnits,
    val elevation: Double,
    @SerialName("generationtime_ms")
    val generationTime: Double,
    val hourly: Hourly,
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)

@Serializable
data class Current(
    @SerialName("apparent_temperature")
    val feelLikeTemperature: Double,
    val interval: Int,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("pressure_msl")
    val pressure: Double,
    val rain: Double,
    @SerialName("relative_humidity_2m")
    val humidity: Int,
    @SerialName("temperature_2m")
    val temperature: Double,
    val time: String,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("wind_speed_10m")
    val windSpeed: Double
)

@Serializable
data class CurrentUnits(
    @SerialName("apparent_temperature")
    val feelLikeTemperature: String,
    val interval: String,
    @SerialName("is_day")
    val isDay: String,
    @SerialName("pressure_msl")
    val pressure: String,
    val rain: String,
    @SerialName("relative_humidity_2m")
    val humidity: String,
    @SerialName("temperature_2m")
    val temperature: String,
    val time: String,
    @SerialName("weather_code")
    val weatherCode: String,
    @SerialName("wind_speed_10m")
    val windSpeed: String
)

@Serializable
data class Daily(
    @SerialName("temperature_2m_max")
    val highTemperatures: List<Double>,
    @SerialName("temperature_2m_min")
    val lowTemperatures: List<Double>,
    @SerialName("time")
    val times: List<String>,
    @SerialName("uv_index_max")
    val uvIndices: List<Double>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>
)

@Serializable
data class DailyUnits(
    @SerialName("temperature_2m_max")
    val highTemperature: String,
    @SerialName("temperature_2m_min")
    val lowTemperature: String,
    val time: String,
    @SerialName("uv_index_max")
    val uvIndex: String,
    @SerialName("weather_code")
    val weatherCode: String
)

@Serializable
data class Hourly(
    @SerialName("temperature_2m")
    val temperatures: List<Double>,
    @SerialName("time")
    val times: List<String>,
    @SerialName("weather_code")
    val weatherCodes: List<Int>
)

@Serializable
data class HourlyUnits(
    @SerialName("temperature_2m")
    val temperatures: String,
    val time: String,
    @SerialName("weather_code")
    val weatherCodes: String
)