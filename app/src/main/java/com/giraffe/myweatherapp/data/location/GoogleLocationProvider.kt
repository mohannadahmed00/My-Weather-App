package com.giraffe.myweatherapp.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.giraffe.myweatherapp.data.response.LocationResponse
import com.giraffe.myweatherapp.domain.exception.NetworkError
import com.giraffe.myweatherapp.domain.exception.Result
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CompletableDeferred
import java.util.Locale


class GoogleLocationProvider(private val context: Context) : LocationProvider {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Result<LocationResponse, NetworkError> {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val accuracy =
            if (true) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY
        val task = fusedLocationProviderClient.getCurrentLocation(
            accuracy,
            CancellationTokenSource().token,
        )
        val def = CompletableDeferred<Result<LocationResponse, NetworkError>>()
        task.addOnSuccessListener {
            val locationResponse = LocationResponse(
                locationName = getLocationDetails(task.result.latitude, task.result.longitude),
                latitude = task.result.latitude,
                longitude = task.result.longitude
            )
            def.complete(Result.Success(locationResponse))
        }.addOnFailureListener {
            def.complete(Result.Error(NetworkError.LOCATION_FETCH_ERROR))
        }
        return def.await()
    }

    private fun getLocationDetails(lat: Double, long: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: MutableList<Address?>? = geocoder.getFromLocation(lat, long, 1)
        val address = addresses?.get(0)?.getAddressLine(0) ?: "no city name, no county name"
        return address.split(",").takeLast(2).let {
            "${it.first()}, ${it.last()}"
        }
    }
}