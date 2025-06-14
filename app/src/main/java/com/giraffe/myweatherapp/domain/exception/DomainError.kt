package com.giraffe.myweatherapp.domain.exception

interface DomainError

enum class NetworkError: DomainError {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
    LOCATION_FETCH_ERROR,
}

enum class LocationError: DomainError {
    INVALID_LOCATION,
}