package com.psbapp.navigation.utils

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.replaceAl(
    nextDestinationId: Int,
) {
    val currentDestination = this.currentDestination
    if (currentDestination != null) {
        val navOptions =
                androidx.navigation.navOptions {
                    popUpTo(currentDestination.route ?: "") { inclusive = true }
                }
        this.navigate(
            nextDestinationId,
            navOptions
        )
    }
}

fun <T : Any> NavController.push(
    nextDestinationId: T,
) {
    navigate(nextDestinationId)
}

fun <T : Any> NavController.replace(
    nextDestinationId: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    popBackStack()
    navigate(
        nextDestinationId,
        navOptions,
        navigatorExtras
    )
}

// Navigate to a destination, clearing all previous destinations
fun <T : Any> NavController.replaceAll(
    nextDestinationId: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    val options = navOptions ?: androidx.navigation.navOptions { popUpTo(0) { inclusive = true } }
    navigate(
        nextDestinationId,
        options,
        navigatorExtras
    )
}

fun <T : Any> NavController.replaceUntil(
    nextDestination: T,
    popUpToRoute: Any,
    inclusive: Boolean = false,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    popBackStack(
        popUpToRoute,
        true
    )
    navigate(
        nextDestination,
        navOptions,
        navigatorExtras
    )
}

// Pop the top destination off the back stack
fun NavController.pop() {
    popBackStack()
}

// Pop all destinations off the back stack
fun NavController.popAll() {
    val startDestination = graph.startDestinationRoute
    if (startDestination != null) {
        popBackStack(
            startDestination,
            true
        )
    }
}

// Pop back to a specific destination
fun <T : Any> NavController.popUntil(destinationId: T, inclusive: Boolean = false) {
    popBackStack(
        destinationId,
        inclusive
    )
}

// Navigate to bottom navigation item with proper state management
fun <T : Any> NavController.navigateToBottomNavItem(route: T) {
    // Only navigate if we're not already on this route
    val currentDestinationRoute = currentBackStackEntry?.destination?.route
    if (currentDestinationRoute != route::class.qualifiedName) {
        navigate(route) {
            // Pop up to the first destination in the graph to avoid building up a large back stack
            popUpTo(graph.startDestinationId) { saveState = true }
            // Avoid multiple copies of the same destination when re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }
}
