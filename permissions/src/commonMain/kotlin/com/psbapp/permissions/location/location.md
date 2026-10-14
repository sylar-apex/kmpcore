# Package: `permissions/location`

## `rememberLocationPermissionState(): KLocationPermissionState`

Composable that reports the current location permission and the actions to change it.

### `KLocationPermissionState`

| member | type | notes |
|---|---|---|
| `isGranted` | `Boolean` | |
| `isPermanentlyDenied` | `Boolean = false` | user chose "don't ask again" |
| `requestPermission` | `() -> Unit` | shows the system prompt |
| `openSettings` | `() -> Unit = {}` | app settings, for the permanently-denied case |

```kotlin
val permission = rememberLocationPermissionState()

when {
    permission.isGranted -> MapContent()
    permission.isPermanentlyDenied -> EnableInSettings(onClick = permission::openSettings)
    else -> RequestAccess(onClick = permission::requestPermission)
}
```

**Gotchas**
- Requesting while `isPermanentlyDenied` does nothing — route the user to settings instead.
- Desktop/web actuals report granted (or no-op) since there is no runtime prompt; branch on platform
  where behaviour must differ.
- Declaring the platform manifest/plist entries is still the app's job.

---

## See also

- [`kmap`](../../../../../../../../kmap/kmap.md) — maps and location that consume this state
