<div align="center">

# kmpcore

**Reusable Kotlin Multiplatform building blocks for Compose apps**

![Kotlin](https://img.shields.io/badge/Kotlin-2.4-7F52FF?logo=kotlin&logoColor=white)
![Compose Multiplatform](https://img.shields.io/badge/Compose_Multiplatform-1.12-4285F4?logo=jetpackcompose&logoColor=white)
![Platforms](https://img.shields.io/badge/platforms-Android%20%7C%20iOS%20%7C%20Desktop%20%7C%20Web-152438)

</div>

`kmpcore` holds the app-agnostic layers every feature module builds on: an MVI base, typed navigation helpers, Koin bootstrap, utilities, key-value storage and permissions. It contains no app-specific code, so any Kotlin Multiplatform app can include it.

## Modules

| Module | What it gives you |
|---|---|
| [`mvi`](mvi/mvi.md) | `MVIBaseViewModel<Event, State, Effect>`, `UiEvent` / `UiState` / `UiEffect`, `ErrorEffect`, and `MviBridge` for non-Compose hosts |
| [`navigation`](navigation/navigation.md) | `PSBNavHost` and typed nav extensions: `push`, `replace`, `replaceAll`, `pop`, `popUntil`, `navigateToBottomNavItem` |
| [`di`](di/di.md) | Koin bootstrap — `initKoin(...)` and `KoinModulesInterface` |
| [`utils`](utils/utils.md) | `DispatcherProvider`, string / number / date helpers, logging, sharing, clipboard, pinch-zoom |
| [`storage/datastore`](storage/datastore/datastore.md) | Multiplatform DataStore preferences with a typed wrapper and cache |
| [`permissions`](permissions/permissions.md) | Runtime permission state for Compose |

Start at [`kmpcore.md`](kmpcore.md) for the full index, and read [`architecture.md`](architecture.md) for the feature-module pattern these libraries are designed for.

## Usage

Add it to your project as a Git submodule:

```bash
git submodule add https://github.com/sylar-apex/kmpcore.git kmpcore
```

Include the modules you need in `settings.gradle.kts`:

```kotlin
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":kmpcore:mvi")
include(":kmpcore:navigation")
include(":kmpcore:di")
include(":kmpcore:utils")
```

Then depend on them from a module:

```kotlin
commonMain.dependencies {
    api(projects.kmpcore.mvi)
    api(projects.kmpcore.navigation)
}
```

The modules read versions from the host project's `gradle/libs.versions.toml` (`javaVersion`, `koin`, `navigationCompose`, `androidx-lifecycle`, `datastore`, …).

## A screen in three pieces

```kotlin
class CounterContract {
    sealed class Event : UiEvent { data object Increment : Event() }
    data class State(val count: Int = 0) : UiState
    sealed class Effect : UiEffect
}

class CounterViewModel : MVIBaseViewModel<CounterContract.Event, CounterContract.State, CounterContract.Effect>() {
    override fun createInitialState() = CounterContract.State()
    override fun handleEffect(effect: CounterContract.Effect) {}
    override fun handleEvent(event: CounterContract.Event) = when (event) {
        CounterContract.Event.Increment -> setState { copy(count = count + 1) }
    }
}
```

> `createInitialState()` runs inside the base-class constructor, before subclass properties are set. Keep it free of constructor parameters and load real data from `init`.

## Used by

- [**MaxPlayer**](https://github.com/sylar-apex/MaxPlayer) — multiplatform video and music player
