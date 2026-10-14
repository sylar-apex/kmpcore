package com.psbapp.permissions.location

import androidx.compose.runtime.Composable

data class KLocationPermissionState(
    val isGranted: Boolean,
    val isPermanentlyDenied: Boolean = false,
    val requestPermission: () -> Unit,
    val openSettings: () -> Unit = {},
)

@Composable
expect fun rememberLocationPermissionState(): KLocationPermissionState
