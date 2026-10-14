# Package: `navigation/utils`

Navigation actions and the host. `AuthGraph.kt` in the app is the reference for how graphs use these.

| I need to… | Use |
|---|---|
| Go to a screen | `navController.push(route)` |
| Replace the current screen | `navController.replace(route)` |
| Go to a screen and clear the stack (login, logout, splash) | `navController.replaceAll(route)` |
| Go back | `navController.pop()` |
| Pop to a specific screen | `navController.popUntil(route, inclusive = false)` |
| Pop everything | `navController.popAll()` |
| Replace up to a screen | `navController.replaceUntil(next, popUpToRoute, inclusive = false)` |
| Switch bottom-nav tab | `navController.navigateToBottomNavItem(route)` |
| Host the graph | `PSBNavHost(navController, startDestination, isAnimationEnabled = true) { … }` |
| Reach the controller deep in the tree | `MaterialNavController` / `LocalNavHostController` |

---

## Actions

| function | behaviour |
|---|---|
| `push(route)` | plain `navigate` |
| `replace(route, navOptions?, navigatorExtras?)` | `popBackStack()` then navigate |
| `replaceAll(route, …)` | navigate with `popUpTo(0) { inclusive = true }` |
| `replaceUntil(next, popUpToRoute, inclusive = false, …)` | pop back to a route, then navigate |
| `pop()` | `popBackStack()` |
| `popAll()` | pop back to the start destination inclusive |
| `popUntil(route, inclusive = false)` | `popBackStack(route, inclusive)` |
| `navigateToBottomNavItem(route)` | tab switch: `popUpTo(start) { saveState }`, `launchSingleTop`, `restoreState`; no-op when already there |
| `replaceAl(destinationId: Int)` | legacy id-based variant — do not use in new code |

```kotlin
composable<HomeRoutes.HomeScreenRoute> {
    HomeScreen(onNavigateAllServices = { navController.push(HomeRoutes.AllServicesScreenRoute) })
}
composable<HomeRoutes.RechargeScreenRoute> { entry ->
    RechargeScreen(subServicesArg = entry.toRoute(), onBackClick = { navController.pop() })
}
```

## `PSBNavHost`

`(navController: NavHostController, startDestination: Any, isAnimationEnabled: Boolean = true, builder: NavGraphBuilder.() -> Unit)`
— `NavHost` with horizontal slide transitions, switchable via `isAnimationEnabled`.

## Controller access

`LocalNavHostController` (composition local) and `MaterialNavController` (read-only accessor). Prefer
passing callbacks down; reach for the local only where threading a lambda is impractical.

**Gotchas**
- Route arguments must be primitives, enums or lists of primitives. A custom class needs a
  `typeMap` + custom `NavType`; the usual workaround is passing json as a `String`.
- `replace` pops **before** navigating, so it drops the current screen even if the navigation fails.
- If no extension fits, add one here rather than calling `NavController` directly in a graph.

---

## See also

- `uidesign/ui/bottomnav` — tab UI that pairs with `navigateToBottomNavItem`
