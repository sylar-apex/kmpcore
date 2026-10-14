# Package: `utils/dispatcher`

Injectable coroutine dispatchers — every ViewModel takes a `DispatcherProvider` instead of touching
`Dispatchers` directly, so tests can swap them.

## `DispatcherProvider`

| member | type |
|---|---|
| `io` | `CoroutineDispatcher` |
| `main` | `CoroutineDispatcher` |
| `default` | `CoroutineDispatcher` |

`DispatcherProviderImpl` is the production implementation; `dispatcherDiModule()` binds it and is part
of `coreDiModule`.

```kotlin
class RechargeViewModel(
    private val dispatcher: DispatcherProvider,
    …
) : MVIBaseViewModel<…>() {
    private fun load() = viewModelScope.launch(dispatcher.io) { … }
}
```

**Gotcha:** never hardcode `Dispatchers.IO` in a feature — inject the provider.

---

## See also

- `uidesign/viewmodel/viewmodel.md` — where the dispatcher is used
- [`utils`](../utils.md) → `coroutineLaunch`, `getCoroutineScope`
