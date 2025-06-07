package com.giraffe.myweatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.myweatherapp.data.DataSource
import com.giraffe.myweatherapp.domain.exception.onError
import com.giraffe.myweatherapp.domain.exception.onSuccess
import com.giraffe.myweatherapp.presentation.model.HomeUiState
import com.giraffe.myweatherapp.presentation.utils.getWeatherDescription
import com.giraffe.myweatherapp.presentation.utils.getWeatherIcon
import com.giraffe.myweatherapp.presentation.utils.toUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val dataSource: DataSource) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getForecast()
    }

    private fun getForecast() {
        viewModelScope.launch {
            dataSource.getForecast()
                .onSuccess { data ->
                    _state.update {
                        it.copy(
                            locationName = data.locationName,
                            currentWeatherIcon = getWeatherIcon(
                                data.currentWeatherCode,
                                data.isDay
                            ),
                            currentTemperature = data.currentTemperature,
                            currentWeatherDescription = getWeatherDescription(data.currentWeatherCode),
                            currentHighTemperature = data.currentHighTemperature,
                            currentLowTemperature = data.currentLowTemperature,
                            windSpeed = data.windSpeed,
                            humidity = data.humidity,
                            rain = data.rain,
                            uv = data.uvIndex,
                            pressure = data.pressure,
                            feelsLike = data.feelsLike,
                            hourlyTemperatures = data.hourlyTemperatures.map { it.toUi() },
                            dailyTemperatures = data.dailyTemperatures.map { it.toUi() },
                            error = null
                        )
                    }
                }.onError { error ->
                    _state.update { it.copy(error = error) }
                }
        }
    }
}