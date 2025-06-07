package com.giraffe.myweatherapp.presentation

interface HomeScreenEvents {
    fun setLocationServiceFlag(isEnabled: Boolean)
    fun setLocationPermissionFlag(isGranted: Boolean)
    fun getCurrentLocation()
}