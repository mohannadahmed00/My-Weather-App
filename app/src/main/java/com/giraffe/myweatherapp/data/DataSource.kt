package com.giraffe.myweatherapp.data

import com.giraffe.myweatherapp.data.location.LocationProvider
import com.giraffe.myweatherapp.data.response.ForecastResponse
import com.giraffe.myweatherapp.data.utils.safeCall
import com.giraffe.myweatherapp.data.utils.toEntity
import com.giraffe.myweatherapp.domain.entity.ForecastEntity
import com.giraffe.myweatherapp.domain.entity.LocationEntity
import com.giraffe.myweatherapp.domain.exception.NetworkError
import io.ktor.client.HttpClient
import com.giraffe.myweatherapp.domain.exception.Result
import com.giraffe.myweatherapp.domain.exception.map
import io.ktor.client.request.get

interface IDataSource {
    suspend fun getForecast(
        lat: Double = 52.52,
        lon: Double = 13.41
    ): Result<ForecastEntity, NetworkError>

    suspend fun getCurrentLocation(): Result<LocationEntity, NetworkError>
}

class DataSource(
    private val httpClient: HttpClient,
    private val locationProvider: LocationProvider
) : IDataSource {
    override suspend fun getForecast(
        lat: Double,
        lon: Double
    ): Result<ForecastEntity, NetworkError> {
        val url =
            "https://api.open-meteo.com/v1/forecast?latitude=$lat&longitude=$lon&daily=weather_code,temperature_2m_min,temperature_2m_max,uv_index_max&hourly=temperature_2m,weather_code&current=temperature_2m,is_day,relative_humidity_2m,apparent_temperature,wind_speed_10m,rain,weather_code,pressure_msl&timezone=auto"
        return safeCall<ForecastResponse> {
            httpClient.get(urlString = url)
        }.map { response -> response.toEntity() }
    }

    override suspend fun getCurrentLocation() = locationProvider.getCurrentLocation().map { it.toEntity() }
}