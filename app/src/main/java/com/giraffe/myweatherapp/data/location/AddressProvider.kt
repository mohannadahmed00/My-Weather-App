package com.giraffe.myweatherapp.data.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.giraffe.myweatherapp.domain.exception.DomainError
import com.giraffe.myweatherapp.domain.exception.LocationError
import com.giraffe.myweatherapp.domain.exception.NetworkError
import com.giraffe.myweatherapp.domain.exception.Result
import java.io.IOException
import java.util.Locale

interface AddressProvider {
    suspend fun getAddress(lat: Double, lon: Double): Result<String, DomainError>
}

class GeoCoderAddressProvider(private val context: Context) : AddressProvider {
    override suspend fun getAddress(lat: Double, lon: Double): Result<String, DomainError> {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: MutableList<Address?>? = geocoder.getFromLocation(lat, lon, 1)
            val address = addresses?.get(0)?.getAddressLine(0) ?: run {
                return Result.Error(NetworkError.LOCATION_FETCH_ERROR)
            }
            Result.Success(address.split(",").takeLast(2).let { "${it.first()}, ${it.last()}" })
        } catch (_: IOException) {
            Result.Error(NetworkError.LOCATION_FETCH_ERROR)
        } catch (_: IllegalArgumentException) {
            Result.Error(LocationError.INVALID_LOCATION)
        }
    }
}