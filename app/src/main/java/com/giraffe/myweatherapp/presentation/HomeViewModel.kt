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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val dataSource: DataSource) : ViewModel(), HomeScreenEvents {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private fun getForecast(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getForecast(lat, lon)
                .onSuccess { data ->
                    _state.update {
                        it.copy(
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
                            isDay = data.isDay,
                            rain = data.rain,
                            uv = data.uvIndex,
                            pressure = data.pressure,
                            feelsLike = data.feelsLike,
                            hourlyTemperatures = data.hourlyTemperatures.map { it.toUi() },
                            dailyTemperatures = data.dailyTemperatures.map { it.toUi() },
                            error = null
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(error = error) }
                }
        }
    }

    override fun getForecastOfCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getCurrentLocation()
                .onSuccess { location ->
                    getForecastOfLocation(location.latitude, location.longitude)
                }.onError { error ->
                    _state.update { it.copy(error = error) }
                }
        }
    }

    override fun getForecastOfLocation(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getAddress(lat, lon).onSuccess { address ->
                _state.update { it.copy(locationName = address) }
                getForecast(lat, lon)
            }.onError { error ->
                _state.update { it.copy(error = error) }
            }
        }
    }

    override fun setLocationServiceFlag(isEnabled: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLocationServiceDialogVisible = !isEnabled) }
        }
    }

    override fun setLocationPermissionFlag(isGranted: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLocationPermissionDialogVisible = !isGranted) }
        }
    }
}