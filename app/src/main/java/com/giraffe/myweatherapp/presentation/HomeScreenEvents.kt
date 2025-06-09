package com.giraffe.myweatherapp.presentation

interface HomeScreenEvents {
    fun getForecastOfCurrentLocation()
    fun getForecastOfLocation(lat: Double, lon: Double)
    fun setLocationServiceFlag(isEnabled: Boolean)
    fun setLocationPermissionFlag(isGranted: Boolean)
}