package com.psbapp.permissions.location

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberLocationPermissionState(): KLocationPermissionState {
    return remember {
        KLocationPermissionState(
            isGranted = true,
            requestPermission = { }
        )
    }
}
