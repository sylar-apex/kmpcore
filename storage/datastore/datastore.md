# Module: `storage/datastore` — Key–Value Preferences

DataStore-backed preferences, shared across platforms.

> Index only — the API lives in [`datastore/datastore.md`](src/commonMain/kotlin/com/psbapp/datastore/datastore.md).

| Doc | Read it when you need |
|---|---|
| [`datastore/datastore.md`](src/commonMain/kotlin/com/psbapp/datastore/datastore.md) | `KDataStorePref` — typed get/set/remove for String, Boolean, Int, Long, Float, Double |
| [`cache/cache.md`](src/commonMain/kotlin/com/psbapp/datastore/cache/cache.md) | `KCache` — key–value cache with ttl, per-call `useCache` switch and a global kill switch |
| [`di/di.md`](src/commonMain/kotlin/com/psbapp/di/di.md) | `kDataStoreModule()` Koin binding |

The app wraps this in `appcore`'s `AppPrefDataStore` (token, user id, …) — **features use that
wrapper**, not `KDataStorePref` directly.
