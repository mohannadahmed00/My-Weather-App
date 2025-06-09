package com.giraffe.myweatherapp.presentation.composable

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.giraffe.myweatherapp.LocalActivity
import com.giraffe.myweatherapp.presentation.utils.areLocationPermissionsGranted
import com.giraffe.myweatherapp.presentation.utils.isLocationPermissionPermanentlyDeclined
import com.giraffe.myweatherapp.presentation.utils.isLocationServicesEnabled
import com.giraffe.myweatherapp.presentation.utils.launchPermissionSettings

@Composable
fun LocationRequirements(
    isLocationPermissionDialogVisible: Boolean,
    isLocationServiceDialogVisible: Boolean,
    setLocationPermissionFlag: (Boolean) -> Unit,
    setLocationServiceFlag: (Boolean) -> Unit,
    onSuccess: () -> Unit
) {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val permissionsToRequest: List<String> = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val multiplePermissionsResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val isGranted = !permissions.containsValue(false)
            setLocationPermissionFlag(isGranted)
            if (isGranted) {
                isLocationServicesEnabled(context) { isEnabled ->
                    setLocationServiceFlag(isEnabled)
                    if (isEnabled) onSuccess()
                }
            }
        },
    )
    LaunchedEffect(Unit) {
        multiplePermissionsResultLauncher.launch(permissionsToRequest.toTypedArray())
    }
    if (isLocationPermissionDialogVisible) {
        LocationPermissionDialog(
            isPermanentlyDeclined = isLocationPermissionPermanentlyDeclined(activity),
            onOkClick = { multiplePermissionsResultLauncher.launch(permissionsToRequest.toTypedArray()) },
            onGoToAppSettingsClick = { launchPermissionSettings(context) },
        ) {
            areLocationPermissionsGranted(context).let { isGranted ->
                setLocationPermissionFlag(isGranted)
                multiplePermissionsResultLauncher.launch(permissionsToRequest.toTypedArray())
            }
        }
    }
    if (isLocationServiceDialogVisible) {
        LocationServiceDialog(context) {
            isLocationServicesEnabled(context) { isEnabled ->
                setLocationServiceFlag(isEnabled)
                if (isEnabled) onSuccess()
            }
        }
    }
}