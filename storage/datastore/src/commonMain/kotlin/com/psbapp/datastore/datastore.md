# Package: `datastore`

## `KDataStorePref(dataStoreName: String)`

Platform DataStore wrapper. All functions are `suspend`.

| type | read | write | remove |
|---|---|---|---|
| String | `getValue(key)` → `String?`, `getString(key, defaultValue = "")` | `setValue(key, value)`, `setString(key, value?)` | `removeString(key)` |
| Boolean | `getBoolean(key, defaultValue = false)`, `getBooleanNullable(key, defaultValue = null)` | `setBoolean(key, value?)` | |
| Int | `getInt(key, defaultValue = 0)`, `getIntNullable(key, …)` | `setInt(key, value?)` | |
| Long | `getLong(key, defaultValue = 0L)`, `getLongNullable(key, …)` | `setLong(key, value?)` | |
| Float / Double | `getFloat` / `getDouble` (+ nullable variants) | `setFloat` / `setDouble` | |

```kotlin
class AppPrefDataStore(private val pref: KDataStorePref) {
    suspend fun getUserId(): String? = pref.getValue(USER_ID)
    suspend fun setUserId(id: String) = pref.setValue(USER_ID, id)
}
```

**Gotchas**
- Writing `null` through a `setX(key, value?)` clears the entry.
- Everything is `suspend` — read it in a coroutine, never during composition.
- One instance per `dataStoreName`; injecting two instances with the same name is undefined.
- Store secrets encrypted (see the `encryption` module); DataStore itself is plain.

---

## See also

- [`cache`](cache/cache.md) — `KCache`, response caching on top of this store
- `appcore` → `AppPrefDataStore` (the app-level wrapper features should use)
- [`di`](../di/di.md)
