package com.giraffe.myweatherapp.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.giraffe.myweatherapp.presentation.utils.getDeclinedDescription

@Composable
fun LocationPermissionDialog(
    isPermanentlyDeclined: Boolean,
    onGoToAppSettingsClick: () -> Unit,
    onOkClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Text(
                text = if (isPermanentlyDeclined) {
                    "Grant permission"
                } else {
                    "OK"
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        if (isPermanentlyDeclined) {
                            onGoToAppSettingsClick()
                        } else {
                            onOkClick()
                        }
                    }
                    .padding(16.dp),
            )
        },
        title = {
            Text("Permission is required")
        },
        text = {
            Text(text = getDeclinedDescription(isPermanentlyDeclined = isPermanentlyDeclined))
        },
    )
}