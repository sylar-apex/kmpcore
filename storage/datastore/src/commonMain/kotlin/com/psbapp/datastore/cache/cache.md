# Package: `datastore/cache`

## `KCache(pref: KDataStorePref)`

Key–value cache on top of `KDataStorePref`: a payload stored under a key with its write time next
to it. Read the key first, call the source only on a miss.

| member | signature | notes |
|---|---|---|
| `get` | `suspend (key: String, useCache: Boolean = true, ttlMillis: Long = TTL_ONE_DAY): String?` | null when missing, stale, `useCache = false`, or `isEnabled` off |
| `set` | `suspend (key: String, value: String, useCache: Boolean = true)` | a blank value, `useCache = false` or `isEnabled` off writes nothing |
| `has` | `suspend (key: String, ttlMillis: Long = TTL_ONE_DAY): Boolean` | live value present |
| `clear` | `suspend (key: String)` | drops one key |
| `KCache.TTL_ONE_DAY` | `Long` | default lifetime |
| `KCache.TTL_FOREVER` | `Long` | state that cannot go stale; dies with the datastore |
| `KCache.isEnabled` | `var Boolean = true` | process wide switch — false makes every read miss and every write a no-op, without clearing what is stored |

```kotlin
cache.set(KEY, json)                      // store
cache.get(KEY)                            // cached value, or null
cache.get(KEY, useCache = false)          // always null → caller refetches
cache.set(KEY, json, useCache = false)    // nothing written
KCache.isEnabled = false                  // whole app runs uncached (debug menu, tests)
```

Injected by `kDataStoreModule()` (already part of `coreDiModule`), so `single { KCache(get()) }` is
available everywhere.

**Gotchas**
- Values are plain strings — objects go through the typed `getObject` / `setObject` extensions in
  `kmpcore/networking/cache/KCacheExt.kt` (`com.psbapp.networking.cache`), which handle the json;
  `Flow.setCache(cache, key)` there parks an api response as it passes, with an optional
  `shouldCache` predicate.
- A negative age (clock moved backwards) counts as stale and drops the key.
- Keys are namespaced internally (`CACHE_` / `CACHE_AT_`), so a cache key never collides with a
  preference of the same name.
- `KDataStorePref.clear()` at logout wipes cached values along with everything else.

---

## See also

- [`datastore`](../datastore.md) — the preferences wrapper underneath
- `kmpcore/networking/cache` → typed `getCache` / `Flow.setCache` (and `getObject` / `setObject`) extensions
- `appcore/cache` → `AppCacheKey` (app-specific slot names)
