package com.giraffe.myweatherapp.data.location

import android.annotation.SuppressLint
import android.content.Context
import com.giraffe.myweatherapp.domain.entity.LocationEntity
import com.giraffe.myweatherapp.domain.exception.NetworkError
import com.giraffe.myweatherapp.domain.exception.Result
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.CompletableDeferred

interface LocationProvider {
    suspend fun getCurrentLocation(): Result<LocationEntity, NetworkError>
}

class GoogleLocationProvider(private val context: Context) : LocationProvider {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Result<LocationEntity, NetworkError> {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val accuracy =
            if (true) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY
        val task = fusedLocationProviderClient.getCurrentLocation(
            accuracy,
            CancellationTokenSource().token,
        )
        val def = CompletableDeferred<Result<LocationEntity, NetworkError>>()
        task.addOnSuccessListener {
            val locationResponse = LocationEntity(
                latitude = task.result.latitude,
                longitude = task.result.longitude
            )
            def.complete(Result.Success(locationResponse))
        }.addOnFailureListener {
            def.complete(Result.Error(NetworkError.LOCATION_FETCH_ERROR))
        }
        return def.await()
    }
}