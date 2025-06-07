package com.giraffe.myweatherapp.data.location

import com.giraffe.myweatherapp.data.response.LocationResponse
import com.giraffe.myweatherapp.domain.exception.NetworkError
import com.giraffe.myweatherapp.domain.exception.Result

interface LocationProvider {
    suspend fun getCurrentLocation(): Result<LocationResponse, NetworkError>
}