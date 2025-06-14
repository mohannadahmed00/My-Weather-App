package com.giraffe.myweatherapp.presentation.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.giraffe.myweatherapp.R
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

//region Weather Utils
//2025-06-10T06:00
fun isDayTime(dateTimeString: String) =
    dateTimeString.substringAfter("T").substringBefore(":").toInt() in 6..18

fun getDayNameFromDate(dateString: String) =
    LocalDate.parse(dateString).dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) ?: "no day"

fun getWeatherIcon(code: Int, isDay: Boolean = true) =
    weatherIconsAndDescriptionsAndDescription[code]?.let { icon ->
        if (isDay) icon.day else icon.night
    } ?: R.drawable.day_mainly_clear

fun getWeatherDescription(code: Int) =
    weatherIconsAndDescriptionsAndDescription[code]?.description ?: "no description"

data class WeatherIconAndDescription(val day: Int, val night: Int? = null, val description: String)

private val weatherIconsAndDescriptionsAndDescription = mapOf(
    0 to WeatherIconAndDescription(
        R.drawable.day_clear_sky,
        R.drawable.night_clear_sky,
        "Clear sky"
    ),
    1 to WeatherIconAndDescription(
        R.drawable.day_mainly_clear,
        R.drawable.night_mainly_clear,
        "Mainly clear"
    ),
    2 to WeatherIconAndDescription(
        R.drawable.day_partly_cloudy,
        R.drawable.night_partly_cloudy,
        "Partly cloudy"
    ),
    3 to WeatherIconAndDescription(R.drawable.overcast, description = "Overcast"),
    45 to WeatherIconAndDescription(R.drawable.fog, description = "Fog"),
    48 to WeatherIconAndDescription(
        R.drawable.depositing_rime_fog,
        description = "Depositing rime fog"
    ),
    51 to WeatherIconAndDescription(R.drawable.drizzle_light, description = "Light drizzle"),
    53 to WeatherIconAndDescription(R.drawable.drizzle_moderate, description = "Moderate drizzle"),
    55 to WeatherIconAndDescription(R.drawable.drizzle_intensity, description = "Dense drizzle"),
    56 to WeatherIconAndDescription(
        R.drawable.freezing_drizzle_light,
        description = "Light freezing drizzle"
    ),
    57 to WeatherIconAndDescription(
        R.drawable.freezing_drizzle_intensity,
        description = "Heavy freezing drizzle"
    ),
    61 to WeatherIconAndDescription(
        R.drawable.day_rain_slight,
        R.drawable.night_rain_slight,
        "Slight rain"
    ),
    63 to WeatherIconAndDescription(
        R.drawable.day_rain_moderate,
        R.drawable.night_rain_moderate,
        "Moderate rain"
    ),
    65 to WeatherIconAndDescription(
        R.drawable.day_rain_intensity,
        R.drawable.night_rain_intensity,
        "Heavy rain"
    ),
    66 to WeatherIconAndDescription(R.drawable.freezing_light, description = "Light freezing rain"),
    67 to WeatherIconAndDescription(R.drawable.freezing_heavy, description = "Heavy freezing rain"),
    71 to WeatherIconAndDescription(
        R.drawable.day_snow_fall_light,
        R.drawable.night_snow_fall_light,
        "Light snowfall"
    ),
    73 to WeatherIconAndDescription(
        R.drawable.day_snow_fall_moderate,
        R.drawable.night_snow_fall_moderate,
        "Moderate snowfall"
    ),
    75 to WeatherIconAndDescription(R.drawable.snow_fall_intensity, description = "Heavy snowfall"),
    77 to WeatherIconAndDescription(R.drawable.snow_grains, description = "Snow grains"),
    80 to WeatherIconAndDescription(
        R.drawable.day_rain_shower_slight,
        R.drawable.night_rain_shower_slight,
        "Slight rain showers"
    ),
    81 to WeatherIconAndDescription(
        R.drawable.day_rain_shower_moderate,
        R.drawable.night_rain_shower_moderate,
        "Moderate rain showers"
    ),
    82 to WeatherIconAndDescription(
        R.drawable.day_rain_shower_violent,
        R.drawable.night_rain_shower_violent,
        "Violent rain showers"
    ),
    85 to WeatherIconAndDescription(
        R.drawable.snow_shower_slight,
        description = "Slight snow showers"
    ),
    86 to WeatherIconAndDescription(
        R.drawable.snow_shower_heavy,
        description = "Heavy snow showers"
    ),
    95 to WeatherIconAndDescription(
        R.drawable.thunderstrom_slight_or_moderate,
        description = "Thunderstorm: slight or moderate"
    ),
    96 to WeatherIconAndDescription(
        R.drawable.thunderstrom_with_slight_hail,
        description = "Thunderstorm with slight hail"
    ),
    99 to WeatherIconAndDescription(
        R.drawable.thunderstrom_with_heavy_hail,
        description = "Thunderstorm with heavy hail"
    )
)
//endregion

//region Location Utils
fun isLocationServicesEnabled(context: Context, onResult: (Boolean) -> Unit) {
    val localeManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = try {
        localeManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    } catch (_: Exception) {
        false
    }

    val isNetworkEnabled = try {
        localeManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } catch (_: Exception) {
        false
    }
    onResult(isGpsEnabled || isNetworkEnabled)
}

fun areLocationPermissionsGranted(context: Context): Boolean {
    return (ActivityCompat.checkSelfPermission(
        context, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
}

fun isLocationPermissionPermanentlyDeclined(activity: ComponentActivity): Boolean {
    val isFinePermanentlyDeclined = !shouldShowRequestPermissionRationale(
        activity,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    val isCoarsePermanentlyDeclined = !shouldShowRequestPermissionRationale(
        activity,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )
    return (isFinePermanentlyDeclined || isCoarsePermanentlyDeclined)
}

fun getDeclinedDescription(isPermanentlyDeclined: Boolean): String {
    return if (isPermanentlyDeclined) {
        "It seems you permanently declined location permission. You can go to app settings to grant it."
    } else {
        "This app needs access to your location permission so that you can get the right forecast."
    }
}

fun launchPermissionSettings(context: Context) {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    intent.data = Uri.fromParts("package", context.packageName, null)
    context.startActivity(intent)
}
//endregion
