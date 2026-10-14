# Module: `mvi`

MVI base classes every feature ViewModel extends, plus the bridge that lets a non-Kotlin front
end drive those same ViewModels. Deliberately free of Compose and of any DI framework, so it
builds for android, iOS, JVM, wasm **and plain Kotlin/JS**.

## `MVIBaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect>`

| member | kind | notes |
|---|---|---|
| `createInitialState()` | abstract | initial `State` |
| `handleEvent(event)` | abstract | reduce events |
| `handleEffect(effect)` | abstract | usually empty `{}` |
| `uiState` | `StateFlow<State>` | collect with `collectAsStateWithLifecycle()` |
| `currentState` | `State` | latest value |
| `event` | `SharedFlow<Event>` | internal pipeline |
| `effect` | `SharedFlow<Effect>` | collect in the screen for navigation |
| `setEvent(event)` | fun | called from UI callbacks |
| `setEffect(effect)` | fun | one-shot (navigate, dismiss) |
| `setState { copy(...) }` | protected | state reduce |
| `updateState { copy(...) }` | protected | atomic reduce (`MutableStateFlow.update`) |

```kotlin
class RechargeViewModel(
    private val dispatcher: DispatcherProvider,
    private val useCase: GetRechargeProvidersUseCase,
) : MVIBaseViewModel<RechargeContract.Event, RechargeContract.State, RechargeContract.Effect>() {

    override fun createInitialState() = RechargeContract.State()
    override fun handleEffect(effect: RechargeContract.Effect) {}

    override fun handleEvent(event: RechargeContract.Event) = when (event) { … }
}
```

**Gotchas**
- `setState` writes `_uiState.value` directly; concurrent reducers can lose updates — prefer
  `updateState` when several coroutines write the same state.
- `event`/`effect` are `MutableSharedFlow` with **no replay**: emissions before a collector attaches are
  lost. `setEvent` from `LaunchedEffect(Unit)` is safe because the VM collects events in `init`.

## `BaseViewModel`

Shared plumbing: `loading: StateFlow<Boolean>` with `startLoading()` / `stopLoading()` /
`combineLoading(vararg Flow<Boolean>)`, and `Flow<T>.stateInEagerly(defaultValue)`.

Messaging lives in [`ui/toastbar`](../ui/toastbar/toastbar.md): `showErrorMsgBar` /
`showSuccessMsgBar` from any `CoroutineScope`.

## Marker interfaces

`UiEvent`, `UiState`, `UiEffect` — implemented by the feature's `Contract` classes.

---

## See also

- `architecture.md` → MVI pattern, Contract/ViewModel/Screen split
- [`ui/toastbar`](../ui/toastbar/toastbar.md), [`ui/view`](../ui/view/view.md)

---

## Errors: `ErrorEffect`

A ViewModel must not decide *how* a failure is shown — a snackbar on Android, a toast in the
browser. It raises an effect carrying the message and each front end renders it its own way:

```kotlin
sealed class Effect : UiEffect {
    data class ShowError(override val message: String) : Effect(), ErrorEffect
}
```

Every UI can then match the marker instead of each feature's own type:

```kotlin
when (effect) { is ErrorEffect -> showErrorMsgBar(effect.message) }
```

`MviBridge.observeErrors` relies on this, so a screen gets web error handling for free.

---

## Driving a ViewModel from another language

| class | source set | use |
|---|---|---|
| `MviBridge` | commonMain | collect a ViewModel from a host that cannot collect flows itself; hands back a `Job` |
| `MviJsBridge` | jsMain | same, returning a `JsSubscription` JavaScript can hold |
| `JsSubscription` | jsMain | `@JsExport` handle with `cancel()` |
| `JsAppGraph` + `bridgeTo()` | jsMain | how a facade finds its ViewModel, without this module knowing the DI framework |

### One-time setup per app

Implement `JsAppGraph` once. This module takes no DI dependency, so Koin, Kodein or plain
factories all work:

```kotlin
object WebAppGraph : JsAppGraph {
    override fun ensureStarted() {
        if (GlobalContext.getOrNull() != null) return
        startKoin { modules(sharedDiModules()) }
    }
    override val mainDispatcher get() = GlobalContext.get().get<DispatcherProvider>().main
    override fun <T : Any> instance(type: KClass<T>): T = GlobalContext.get().get(type)
}
```

### Per screen

Only the typed boundary — the JS state shape and the events. No lifecycle code:

```kotlin
@JsExport
class HomeFacade {
    private val bridge = WebAppGraph.bridgeTo<
        HomeViewModel, HomeContract.Event, HomeContract.State, HomeContract.Effect,
        >()

    fun observeState(onState: (HomeStateJs) -> Unit) = bridge.observeState(State::toJs, onState)
    fun observeErrors(onError: (String) -> Unit) = bridge.observeErrors(onError)
    fun loadHome() = bridge.send(HomeContract.Event.LoadHome)
    fun dispose() = bridge.dispose()
}
```

Alongside it, a `…JsModels.kt` mirroring the state with primitives and arrays — `@JsExport` cannot
carry Kotlin `List` or data classes, and these mirrors are what `generateTypeScriptDefinitions()`
turns into `.d.ts`, so the front end gets real types with no hand-written interfaces.

**Gotchas**
- `dispose()` is terminal: it cancels the scope for good. Build a bridge per mount. React's
  StrictMode mounts, cleans up, then mounts again — a facade created during render and disposed by
  the first cleanup will never emit again.
- Exported types must live in a module with `generateTypeScriptDefinitions()`; that flag is on
  here, and Kotlin re-declares exported types into a consumer's `.d.ts`, so facades in another
  module still type-check.
- `bridgeTo()` is `inline` + `reified`, so everything it touches has to be public API.
