package com.psbapp.navigation.utils

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun PSBNavHost(
    navController: NavHostController,
    startDestination: Any,
    isAnimationEnabled: Boolean = true,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            if (isAnimationEnabled) {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec =
                            tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                ) + fadeIn(animationSpec = tween(durationMillis = 500))
            } else {
                EnterTransition.None
            }
        },
        exitTransition = {
            if (isAnimationEnabled) {
                slideOutHorizontally(
                    targetOffsetX = { -it / 2 },
                    animationSpec =
                            tween(
                                durationMillis = 300,
                                easing = FastOutLinearInEasing
                            )
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            } else {
                ExitTransition.None
            }
        },
        popEnterTransition = {
            if (isAnimationEnabled) {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec =
                            tween(
                                durationMillis = 500,
                                easing = FastOutSlowInEasing
                            )
                ) + fadeIn(animationSpec = tween(durationMillis = 500))
            } else {
                EnterTransition.None
            }
        },
        popExitTransition = {
            if (isAnimationEnabled) {
                slideOutHorizontally(
                    targetOffsetX = { it / 2 },
                    animationSpec =
                            tween(
                                durationMillis = 300,
                                easing = FastOutLinearInEasing
                            )
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            } else {
                ExitTransition.None
            }
        },
        builder = builder
    )
}
