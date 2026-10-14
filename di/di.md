# Module: `di` — Koin Bootstrap

Starts Koin for every platform. One interface, one entry point.

> Index only — the whole module is two files; the API is below.

---

## `KoinModulesInterface`

The app implements it and returns every module to register:

```kotlin
class AppKoinModule : KoinModulesInterface {
    override fun getModules(): List<Module> {
        val modules = mutableListOf(appModule(), homeDiModule(), rechargeDiModule(), …)
        modules.addAll(coreDiModule)     // uidesign/di
        return modules
    }
}
```

## Starting Koin

| platform | function |
|---|---|
| common (iOS / desktop / web) | `initKoin(koinModulesInterface: KoinModulesInterface)` |
| Android | `initKoin(application: Application, koinModulesInterface: KoinModulesInterface)` — also registers `contextModule(application)` so `Context` is injectable |

**Gotchas**
- Call it once, at app start, before any screen composes.
- Forgetting `coreDiModule` compiles fine and fails at the first injection (dispatchers, network,
  datastore, localization, socket).

---

## See also

- `uidesign/di/di.md` → `coreDiModule`, `uidesign/ui/preview` → `PSBPreview(modules = …)`
