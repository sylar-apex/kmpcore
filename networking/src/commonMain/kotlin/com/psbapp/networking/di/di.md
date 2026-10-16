# Package: `networking/di`

## `networkModule()`

Koin module binding `KtorClient` and `NetworkClient`. Part of `coreDiModule`, so registering that
list is enough.

**Gotcha:** it depends on the app's `NetworkInfo` binding — without it, the first API call fails at
injection time, not at startup.

---

## See also

- `uidesign/di/di.md` → `coreDiModule`
