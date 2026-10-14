package com.psbapp.navigation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavHostController =
        compositionLocalOf<NavHostController> { error("NavHostController  Not Found") }

val MaterialNavController: NavHostController
    @Composable
    @ReadOnlyComposable
    get() = LocalNavHostController.current

