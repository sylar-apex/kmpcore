# PayNxtKmp — Architecture Reference

---

> ## ⚠️ STRICT RULES — NO EXCEPTIONS
>
> **Every rule in this document is mandatory. Nothing is optional or a suggestion.**
>
> - Do NOT skip any rule, even if it seems minor or unnecessary for the task.
> - Do NOT assume or guess. If anything is unclear — the right component to use, the right pattern to follow, where something lives, or what the user wants — **STOP and ask first** before writing a single line of code.
> - Do NOT create something that might already exist without searching for it first.
> - Do NOT randomly pick a pattern. Follow what is documented here exactly.
>
> **When in doubt → ask. Always. No exceptions.**
>
> - **Lists and grids are always lazy — never a `forEach` / `for` / `repeat` emitting composables.** Any collection rendered on screen goes through `LazyColumn`, `LazyRow`, `LazyVerticalGrid` / `LazyHorizontalGrid` with `items(...)` / `item(...)` and a stable `key`. No `list.forEach { Card(it) }` inside a `Column`/`Row`, no `chunked(n)` + `Row` to fake a grid, no `repeat(n)` to pad the last row — `LazyVerticalGrid` already handles partial rows. Mixed content (banner, headers, dividers plus a grid) belongs in one lazy container with full-span items, never a scrollable nested inside another scrollable of the same direction.
>   Iteration is allowed **only inside a lazy scope** (`LazyListScope` / `LazyGridScope`) to emit items — e.g. `sections.forEach { item { … }; items(it.rows) { … } }` — because that is list building, not UI emission.
> - **All colours come from `MaterialThemeColor` only.** No `Color(0xFF…)` literals, no feature-local palette objects, no `Color.White` / `Color.Black` in feature code. Use the token that matches the role (`brand.*`, `surfaces.*`, `text.*`, `card.*`, `status.*`, `greyColor.*`); tint an existing token with `.copy(alpha = …)` when a lighter shade is needed.
> - **If the colour you need does not exist in `MaterialThemeColor` — STOP and ask.** Do not hardcode it, do not pick "the closest" token on your own, and do not add a new token to `appres/colors` unprompted. Report which colour is missing and where it is needed, then wait for the user to decide (add a theme token / reuse an existing one / something else). Only proceed after that answer.
> - **Never call `NavController.navigate(...)` or `popBackStack()` directly.** Navigation always goes through the extensions in `com.psbapp.navigation.utils` (`kmpcore/navigation`): `push`, `replace`, `replaceAll`, `replaceUntil`, `pop`, `popAll`, `popUntil`, `navigateToBottomNavItem`. `AuthGraph.kt` is the reference. If no extension fits the case, add one there — do not fall back to the raw `NavController` API in a graph.
> - **No hardcoded user-facing text anywhere.** Every string shown on screen — titles, labels, subtitles, button text, hints, chips, empty states, error/toast copy — must live in `appcore/appres/src/commonMain/composeResources/values/strings.xml` and be read with `stringResource(AppStringRes.some_key)`. Never a string literal in a composable, `const val`, or model default. Use placeholders (`%1$s`) for dynamic parts instead of building text with string templates.
>
> - **`kmpcore/` is frozen without explicit approval.** Never edit any file under `kmpcore/` (including `NetworkClient.kt` and other shared networking/util code) unless the user explicitly approves that specific change first. Feature modules must work around core library limitations locally (e.g. a raw call inside a feature's own `ApiServiceImpl`) rather than modifying shared code. Always ask before proposing a `kmpcore/` change — never make it directly.
> - **Before using anything from `kmpcore` — UI, networking, storage, sockets, maps, auth, utilities, editors — start at [`kmpcore/kmpcore.md`](kmpcore.md).** It routes to every module's own `.md`, which documents the full API, usage snippets and gotchas. Do not guess a `kmpcore` API from a similar-sounding name or from memory.

---

> This document is the single source of truth for how features are built in this project.
> Read it fully before building any new screen, module, layer, component, or utility.

---

## Project Structure

```
PayNxtKmp/
├── androidApp/            Android entry point
├── iosApp/                iOS entry point
├── desktopApp/            Desktop entry point
├── sharedLogic/           (only if the project has a ReactJS web app) Kotlin/JS facades — see "Web (ReactJS) integration"
├── webApp/                (only if the project has a ReactJS web app) React + Vite entry point
│
├── appcore/               App-level shared code
│   ├── appres/            Resources: drawables, fonts, colors, strings
│   └── src/               AppTopBar, AppBackButton, MunafaPlusButton, network Endpoints
│
├── appfeatures/           Feature modules (one per feature)
│   └── auth/
│       ├── login/
│       │   ├── domain/         Gradle module — business logic, JS-buildable, no Compose
│       │   └── presentation/   Gradle module — Compose UI, depends on :login:domain
│       ├── otp/
│       ├── register/
│       ├── splash/
│       └── ...
│
└── kmpcore/               Core KMP libraries (no app-specific code)
    ├── uidesign/          Design system (components, theme, ViewModel base)
    ├── networking/        Ktor client, NetworkState, BaseResponse, Mapper
    ├── utils/             DispatcherProvider, downTimer, getKeyboard, EMPTY
    ├── di/                Koin setup
    └── ...
```

---

## Feature Module Structure

Every feature under `appfeatures/` is **two separate Gradle modules**, `domain` and `presentation` —
not two folders inside one module. `data/` does not exist as a third module or folder; repository
implementations live inside `domain` (see the tree below). Login and OTP are the canonical examples.

```
appfeatures/auth/login/
├── domain/                             Gradle module — :appfeatures:auth:login:domain
│   ├── build.gradle.kts                targets: android, ios, desktop, js — NO Compose plugin, NO androidx.compose.* import
│   └── src/commonMain/kotlin/…/login/
│       ├── di/
│       │   └── FeatureDomainModule.kt  Koin module: mapper, api service, repository, usecase(s), ViewModel
│       ├── domain/
│       │   ├── constant/
│       │   │   ├── FeatureLimits.kt    object FeatureLimits { const val … } — field lengths, counts
│       │   │   └── FeatureListKey.kt   object FeatureListKey { … } — lazy list keys tied to a fixed data shape
│       │   ├── model/
│       │   │   ├── FeatureForm.kt      Form payload handed from screen to ViewModel
│       │   │   └── FeatureResponseData.kt  Domain model (extends BaseResponse)
│       │   ├── repository/
│       │   │   └── FeatureRepository.kt    Interface
│       │   ├── usecase/
│       │   │   └── FeatureUseCase.kt   One class, one or more suspend funs
│       │   └── validation/
│       │       └── FeatureValidation.kt    Form rules as extensions: Form.validate(onError)
│       ├── data/
│       │   ├── mapper/FeatureMapper.kt     Mapper<Domain, BaseResponse<DTO>, Unit>
│       │   └── repository/FeatureRepositoryImpl.kt
│       ├── remote/
│       │   ├── Endpoints.kt            object { val X = "$BASE_URL/..." }
│       │   ├── FeatureApiService.kt    Interface
│       │   ├── FeatureApiServiceImpl.kt    Ktor calls via NetworkClient
│       │   └── dto/
│       │       ├── request/FeatureRequest.kt   @Serializable data class
│       │       └── response/FeatureResponse.kt @Serializable data class
│       └── ui/
│           ├── FeatureContract.kt      Event / State / Effect sealed classes
│           └── FeatureViewModel.kt     MVIBaseViewModel — no Compose import
│
└── presentation/                       Gradle module — :appfeatures:auth:login:presentation
    ├── build.gradle.kts                Compose Multiplatform plugin; depends on :login:domain + kmpcore/uidesign; no js target needed
    └── src/commonMain/kotlin/…/login/ui/
        ├── FeatureScreen.kt            FeatureScreen (koinInject VM) + FeatureContent (pure UI) + @Preview
        ├── model/
        │   └── FeatureUiModel.kt       rememberSaveable + Saver
        ├── constant/
        │   ├── FeatureLayoutSpec.kt    dp/sp/alpha/weight values used only by this feature's Compose UI
        │   └── FeatureFormat.kt        separators/format strings used only by this feature's Compose UI
        └── component/
            └── SomeComponent.kt        Extracted sub-composables (internal)
```

`presentation` has no DI module of its own — `FeatureScreen` resolves the ViewModel with
`koinInject()` from the `domain` module's Koin graph. Never split a feature any other way (no
`data` Gradle module, no Compose code inside `domain`, no business logic inside `presentation`).

---

## Layer Responsibilities

| Module | Layer | Responsibility | Key types |
|---|---|---|---|
| `domain` | `remote/dto` | Serialization shapes | `@Serializable data class` |
| `domain` | `remote/ApiService` | HTTP calls | `NetworkClient.ktorPostFlowRequest` |
| `domain` | `data/mapper` | DTO → Domain | `Mapper<Domain, BaseResponse<DTO>, Unit>` |
| `domain` | `domain/model` | Business data | `data class extends BaseResponse` |
| `domain` | `data/repository` + `domain/repository` | Interface + impl | `Flow<NetworkState<Domain>>` |
| `domain` | `domain/usecase` | Single responsibility | suspend fun returning Flow |
| `domain` | `domain/validation` | Form rules | `fun Form.validate(onError): Boolean` |
| `domain` | `domain/constant` | Named business-constant objects | `object FeatureLimits { const val … }` |
| `domain` | `ui/Contract` | MVI contract | `Event`, `State`, `Effect` |
| `domain` | `ui/ViewModel` | Business logic | `MVIBaseViewModel` — no Compose import |
| `presentation` | `ui/Screen` | UI only | `FeatureScreen` + `FeatureContent` |
| `presentation` | `ui/model` | Saveable UI state | `FeatureUiModel` (+ `.from(form)` for resume) |
| `presentation` | `ui/constant` | Named UI-only constant objects | `object FeatureLayoutSpec { val … }` |

---

## DTO Convention

Every field in a `remote/dto/response` `@Serializable data class` must:
- carry an explicit `@SerialName("...")`, even when it matches the property name — backend key renames then only touch the annotation, not call sites.
- be nullable with `= null` default — a missing/null backend field must not break parsing.

```kotlin
@Serializable
data class FeatureResponse(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
)
```

In the mapper, resolve nulls to defaults using the `com.psbapp.utils` extensions (`String?.value` → `""`, `String?.valueNa` → `"N/A"`, etc.) rather than inline `?:` — keeps default-value policy in one place.

```kotlin
private fun FeatureResponse.toDomain() = FeatureDomain(
    id = id ?: 0,
    name = name.value.trim(),
)
```

### Wire-string enums

When the backend sends a fixed set of string values for something the app treats as an enum (a
status, a type, a role), give the enum its wire value and a `fromWire` — never a loose string
constant (`const val STATUS_ACTIVE = "active"`) compared inline in a mapper:

```kotlin
enum class FeatureStatus(val wireValue: String) {
    PENDING("pending"),
    ACTIVE("active");

    companion object {
        fun fromWire(value: String?): FeatureStatus = entries.firstOrNull { it.wireValue == value } ?: PENDING
    }
}
```

One place to see every value the backend can send, and the fallback for an unknown/future value is
explicit instead of an inline `?:` at each call site.

### Cross-repo shared constants

Anything whose value must literally match the backend — an OTP length, a TTL, a regex, a wire-enum's
string set — gets a one-line comment on both sides naming the file it mirrors (`PartnerAuthLimits.ts`
on the backend ↔ `OtpLimits.kt` here). The backend's own `nestcore/architecture.md` documents the
matching `domain/constant/` rule on that side (its rule 15) — read both together when touching a
value shared across the two repos.

---

## MVI Pattern

### Contract
```kotlin
class FeatureContract {
    sealed class Event : UiEvent {
        data class DoSomething(val param: String) : Event()
    }
    data class State(
        val loading: Boolean = false,
        // ...other UI state
    ) : UiState
    sealed class Effect : UiEffect {
        data object NavigateSomewhere : Effect()
    }
}
```

### ViewModel
```kotlin
class FeatureViewModel(
    private val dispatcher: DispatcherProvider,
    private val useCase: FeatureUseCase
) : MVIBaseViewModel<FeatureContract.Event, FeatureContract.State, FeatureContract.Effect>() {

    override fun createInitialState() = FeatureContract.State()
    override fun handleEffect(effect: FeatureContract.Effect) {}

    override fun handleEvent(event: FeatureContract.Event) {
        when (event) {
            is FeatureContract.Event.DoSomething -> handleDoSomething(event)
        }
    }

    private fun handleDoSomething(event: FeatureContract.Event.DoSomething) {
        viewModelScope.launch(dispatcher.io) {
            useCase.doSomething(event.param)
                .onLoading { setState { copy(loading = it) } }
                .onSuccess { setEffect(FeatureContract.Effect.NavigateSomewhere) }
                .onError { showErrorMsgBar(it) }
                .collect()
        }
    }
}
```

### Screen split (Login/OTP pattern)
```kotlin
// FeatureScreen — wires ViewModel, collects state/effects
@Composable
fun FeatureScreen(
    viewModel: FeatureViewModel = koinInject(),
    onNavigateSomewhere: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                FeatureContract.Effect.NavigateSomewhere -> onNavigateSomewhere()
            }
        }
    }
    FeatureContent(
        loading = state.loading,
        onDoSomething = { viewModel.setEvent(FeatureContract.Event.DoSomething(it)) }
    )
}

// FeatureContent — pure UI, no ViewModel dependency
@Composable
fun FeatureContent(
    loading: Boolean = false,
    onDoSomething: (String) -> Unit = {}
) {
    val model = rememberSaveable(saver = FeatureUiModel.Saver) { FeatureUiModel() }
    ViewScreen(loading = loading) { /* UI here */ }
}

// Preview always calls FeatureContent, never FeatureScreen
@Preview
@Composable
fun FeaturePreviewScreen() {
    PSBPreview(themeConfig = getAppThemeConfig()) {
        FeatureContent()
    }
}
```

---

## UI Model (rememberSaveable)

Holds UI-only state that must survive configuration changes.
Timer state and other self-managed component state do NOT go here.

```kotlin
@Stable
class FeatureUiModel(
    someValue: String = String.EMPTY
) {
    val someValue = mutableStateOf(someValue)

    companion object {
        val Saver = listSaver(
            save = { listOf(it.someValue.value) },
            restore = { FeatureUiModel(someValue = it[0]) }
        )
    }
}
```

Use `mutableIntStateOf` (not `mutableStateOf`) for `Int` fields to avoid boxing.

Usage in composable:
```kotlin
val model = rememberSaveable(saver = FeatureUiModel.Saver) { FeatureUiModel() }
var someValue by model.someValue  // delegates into model
```

---

## DI Module

Lives entirely in the `domain` module — `presentation` never declares its own Koin module.

```kotlin
fun featureDomainModule() = module {
    factory { FeatureMapper() }
    factory<FeatureApiService> { FeatureApiServiceImpl(get()) }
    factory<FeatureRepository> { FeatureRepositoryImpl(get(), get()) }
    factory { FeatureUseCase(get()) }
    factory { FeatureViewModel(get(), get()) }  // dispatcher, useCase
}
```

Register `featureDomainModule()` in the app's Koin setup (`sharedDiModules()`). `FeatureScreen` in
`presentation` resolves the ViewModel with plain `koinInject()` — it never constructs one itself.

---

## Design System

> **Full reference: [`kmpcore/uidesign/uidesign.md`](uidesign/uidesign.md).** It routes every UI task to
> the package that solves it, and each package carries its own `<package>.md` with all functions,
> params, defaults, usage and gotchas. The summary below is a quick orientation only — the package docs
> are authoritative.

### Colors
```kotlin
MaterialThemeColor.brand.primaryDark    // #0D1B33  navy (buttons, title text)
MaterialThemeColor.brand.secondary      // #D79B16  gold (accents, icons, action text)
MaterialThemeColor.brand.secondaryLight // #F5C24B  gold light
MaterialThemeColor.surfaces.background  // white/light bg
MaterialThemeColor.surfaces.surfaceVariant // #F5F7FA  card/section bg
MaterialThemeColor.text.grey500         // #6B7280  subtitle/hint text
MaterialThemeColor.text.secondary       // action color
```

### Typography styles (all @Composable)
```kotlin
primaryDarkStyle   // navy dark text — titles, email
greyStyle          // grey text — subtitles, hints
blackStyle         // black text — card content
whiteStyle         // white text — button labels
normalStyle        // default weight
boldStyle / semiBoldStyle / extraBoldStyle
```

**Colour not in `MaterialThemeColor`?** Stop. Say which colour is missing and where it is needed, and ask
the user what to do — never hardcode a hex value, never silently substitute a near-enough token, never add
a new token to `appres/colors` yourself. Continue only after the user answers.

Copy style then override specific fields:
```kotlin
primaryDarkStyle.copy(fontSize = TextDimension.sp22)
greyStyle.copy(fontSize = TextDimension.sp12, color = MaterialThemeColor.brand.secondary)
```

### Dimensions
```kotlin
MaterialDimension.dp{N}    // use .sdp (scaled dp) — dp2 through dp687
TextDimension.sp{N}        // use .ssp (scaled sp)
```

Common values: `dp3, dp6, dp8, dp10, dp12, dp14, dp15, dp16, dp20, dp24, dp30, dp38, dp40, dp44, dp48, dp50, dp80, dp200`

### TextWeight
```kotlin
TextWeight.REGULAR / MEDIUM / SEMI_BOLD / BOLD / EXTRA_BOLD
```

---

## UI Components

### Screen wrapper
```kotlin
ViewScreen(loading = false) { /* content */ }
// Adds loading overlay, toast bar, keyboard auto-hide
```

### Top bar
```kotlin
AppTopBar(onLeadingIconClick = onBackClick)  // from appcore
```

### Button
```kotlin
PSBButton(text = "Label", enable = true, onClick = {})
// bgColor default = brand.primaryDark, roundCorner = dp30, height = dp38

MunafaPlusButton(text = "Label", onClick = {})     // app-specific wrapper
```

Custom button with trailing icon — use `Button` directly with `Box` content:
```kotlin
Button(...) {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        PSBText("Label", ...)
        PSBVectorImageView(Modifier.align(Alignment.CenterEnd), image = ..., tintColor = ...)
    }
}
```

### Text
```kotlin
PSBText(text = "...", style = primaryDarkStyle.copy(...), fontWeight = TextWeight.BOLD)
```

### Images
```kotlin
PSBImageView(image = AppDrawableRes.img_otp, contentScale = ContentScale.Crop)  // raster (PNG/WebP)
PSBVectorImageView(image = AppDrawableRes.ic_arrow, tintColor = ...)             // XML vector
PSBBgVectorImage(image = AppDrawableRes.ic_shield, bgColor = ..., alpha = 0.15f) // icon with tinted bg circle
```

### OTP Input
```kotlin
OtpInputTextField(
    otpText = otpValue,
    otpLength = 4,
    onOtpModified = { otp, filled -> otpValue = otp; if (filled) keyboard?.hide() },
    showInvalidOTP = showInvalidOtp  // from ViewModel state
)
```

### Resend Code (has built-in 59s countdown timer)
```kotlin
ResendCode(
    textColor = MaterialThemeColor.text.grey500,
    actionTextColor = MaterialThemeColor.brand.secondary,
    resendClick = { /* call ViewModel event */ }
)
// Do NOT manage timer state externally — ResendCode owns it
```

### Spacers
```kotlin
VerticalSpacer(MaterialDimension.dp16)
HorizontalSpacer(MaterialDimension.dp8)
MaxSpacer()   // fills remaining space (RowScope or ColumnScope)
```

### Preview wrapper
```kotlin
PSBPreview(themeConfig = getAppThemeConfig()) { /* content */ }
```

---

## Key Drawables (`AppDrawableRes.{name}`)

| Name | Type | Use |
|---|---|---|
| `img_otp` | WebP | OTP screen hero image |
| `img_login_right` | PNG | Login hero image |
| `ic_otp` | XML vector | OTP icon (small) |
| `ic_secure_sheild` | XML vector | Security badge icon |
| `ic_lock` | XML vector | Lock icon |
| `ic_time` | PNG | Clock/timer icon |
| `ic_app_arrow_forward` | XML vector | Forward arrow (button trailing icon) |
| `ic_app_arrow_right` | WebP | Right arrow |
| `app_logo` | PNG | App logo |

---

## Validation

**Input validation never lives in a composable, and the rules themselves live in
`domain/validation/`, not inside the ViewModel.** The Content composable collects input and fires one
event carrying the raw fields; the ViewModel calls the form's `validate(...)` extension and stops at
the first failure. `IAepsEkycValidation` (driver) and `CcValidation` (credit card driver) are the
references.

The rules name a `StringResource` rather than a sentence, so the ViewModel — the only layer that can
suspend — resolves it with `getString`:

```kotlin
// domain/validation/FeatureValidation.kt — one file, one extension per form
data class FeatureValidationError(
    val message: StringResource,
    val args: List<String> = emptyList(),
)

fun FeatureForm.validate(onError: (FeatureValidationError) -> Unit): Boolean {
    val error = when {
        name.isBlank() -> AppStringRes.feature_error_name
        mobile.length != FeatureLimits.MOBILE_LENGTH -> AppStringRes.feature_error_mobile
        !email.isValidEmail() -> AppStringRes.feature_error_email
        else -> null
    } ?: return true

    onError(FeatureValidationError(error))
    return false
}
```

```kotlin
// ViewModel — calls the rule, resolves the message in one place
private fun handleSubmit(event: Event.Submit) {
    viewModelScope.launch(dispatcher.io) {
        if (!event.form.validate { reportInvalid(it) }) return@launch
        useCase(...)…
    }
}

private fun reportInvalid(error: FeatureValidationError) {
    viewModelScope.launch(dispatcher.io) {
        showErrorMsgBar(getString(error.message, *error.args.toTypedArray()))
    }
}
```

A rule that needs more than the form itself takes it as a parameter (`form.validate(route) { … }`) —
the extension stays pure, so it is testable without a ViewModel or a Koin graph.

```kotlin
// Screen — no checks, no error strings, one line
MunafaPlusButton(text = stringResource(AppStringRes.feature_submit), onClick = { onSubmit(model.toForm()) })
```

- Reuse the validators in `uidesign/ui/edittext/validation` (`isValidEmail`, `isValidPassword`) —
  never hand-roll a regex in a feature.
- The `UiModel` owns the fields and exposes `toForm()`; the screen never assembles the payload
  inline and never keeps a second copy of a field in a local `remember`.
- Prefill from the profile/api goes through a `seed(...)` on the `UiModel` that only fills fields
  the user has not typed into. `remember(state.value) { mutableStateOf(...) }` is a bug: it wipes
  what the user typed whenever state changes.

---

## Constants

**No magic numbers or loose `private const val` in screen files.** Field lengths, item counts, api
type strings and the like live in a named object in their own file:

| Constant | Where it belongs |
|---|---|
| Field lengths, otp length, amount caps | `domain/constant/FeatureLimits.kt` — `object FeatureLimits { … }` |
| Lazy list keys for fixed rows/sections | `domain/constant/FeatureListKey.kt` — `object FeatureListKey { … }` |
| Response sentinels shared by more than one file | `domain/constant/FeatureFlags.kt` |
| Api type/flag values sent in a request | `remote/Endpoints.kt` next to the url that uses them |
| Response sentinel read in one mapper only | private const in that mapper |

**Every constant object lives under `domain/constant/`** — one object per concern, never a loose
`private const val` in a screen for anything a request, a rule or a list identity depends on.

Grid column counts and other pure layout numbers may stay a `private const val` in the component
that draws them. Repeated design values (tint alphas, accent cycles) belong in one named object in
the feature's `ui/component/` instead of being redeclared per file.

---

## Runtime permissions

A ViewModel cannot raise a system prompt — `rememberLocationPermissionState()` only exists in
composition. So the ViewModel asks for it through an effect:

```kotlin
// ViewModel — the api call needs coordinates
private suspend fun getLocation(): KLocation? = try {
    locationManager.kLocationService.getCurrentLocation()
} catch (e: LocationPermissionDeniedException) {
    setEffect(Effect.RequestLocationPermission)
    null
}

// Screen — raises the prompt, or routes to settings when it is permanently denied
is Effect.RequestLocationPermission -> {
    if (currentLocationPermission.isPermanentlyDenied) {
        scope.showErrorMsgBar(locationRequired)
    } else {
        currentLocationPermission.requestPermission()
    }
}
```

Never pre-check `isGranted` in a button's `onClick` — that puts policy in the UI and silently
swallows the permanently-denied case.

---

## Networking

```kotlin
// All API calls return Flow<NetworkState<BaseResponse<DTO>>>
// Collect with:
flow
    .onLoading { isLoading -> setState { copy(loading = isLoading) } }
    .onSuccess { data -> /* handle success */ }
    .onError { message -> showErrorMsgBar(message) }
    .collect()

// Base URL
Endpoints.BASE_URL = "https://yourdomain/api"
```

### Response envelope contract

**Every backend response must be `{status, message, data: {...}}`** — the real payload always
sits under `data`, never flat at the top level. This is what `BaseResponse<DTO>` (`data: T?`)
already assumes.

**If any endpoint ever responds without the `data` wrapper (fields flat alongside `status`/
`message`), that is a backend bug, not something to work around client-side.** Flag it — don't
add a special-case DTO/parsing path for it. It should be fixed on the backend to match the
standard envelope.

### Business failures inside a 2xx

A http 200 body such as `{"status":"failed","message":"…"}` arrives as `NetworkState.Success`, so
`onSuccess` has to check the envelope before acting on the payload. Use the helpers in
`appcore/network`: `BaseResponse.isFailed()` and `BaseResponse.failureMessage(fallback)`. Domain
models that need this extend `BaseResponse` and carry `responseStatus` / `responseMessage`.

### Never short-circuit a network flow

**Do not use `first()`, `firstOrNull()`, `take(n)` or any other cancelling terminal operator on a
`NetworkClient` flow.** They cancel the flow with `AbortFlowException`, whose `catch` block then
emits an error into a dead collector, and the app dies with
*"Flow exception transparency is violated"*. Collect to the end and capture what you need:

```kotlin
var onboarded = false
getCaepsDataUseCase()
    .onSuccess { data -> onboarded = !data.isFailed() && data.isOnboarded }
    .onError { onboarded = false }
    .collect()
```

### Caching a response

Cached endpoints go through `KCache` (`kmpcore/storage/datastore`, injected by `kDataStoreModule()`)
with a slot named in `AppCacheKey` (`appcore/cache`). Responses are read and written **in the
repository**, never in a ViewModel, through the typed `getCache` / `setCache` (and lower-level
`getObject` / `setObject`) extensions in `com.psbapp.networking.cache` (`kmpcore/networking`):

```kotlin
override suspend fun getData(): Flow<NetworkState<Domain>> {
    cache.getCache<Dto>(AppCacheKey.SLOT)?.let { cached ->
        return flowOf(NetworkState.Success(mapper.dtoToDomain(cached)))
    }
    return apiService.getData()
        .setCache(cache, AppCacheKey.SLOT)                       // or { shouldCache(it) }
        .mapApiData { response -> mapper.dtoToDomain(response) }
}
```

- Only cache an answer that cannot go stale in a way that breaks the screen; cache conditionally
  when only one outcome is final (e.g. write only once a user is onboarded).
- `ttlMillis` defaults to `KCache.TTL_ONE_DAY`; use `KCache.TTL_FOREVER` for state that cannot go
  stale on its own.
- Every call takes `useCache`, and `KCache.isEnabled = false` turns caching off process wide —
  use those to work against live data instead of deleting the cache code while testing.
- Slots die with the datastore, which `dataStore.clear()` wipes at logout — no separate cleanup.
- Add a new slot to `AppCacheKey` with a comment saying what is cached and for how long.

---

## Web (ReactJS) integration

> **Only follow this section if the project has a ReactJS web app** (a `webApp/` React+Vite
> project alongside the KMP apps). If the project has no React web app, ignore this section
> entirely — nothing else in this document depends on it, and no `sharedLogic` module is created.

The React app never gets its own business logic. It reuses the exact same `domain` module —
ViewModel, use case, repository, validation, everything — that the Compose apps run. Nothing about
the `domain`/`presentation` split above changes; React is a third consumer of the same `domain`
module, sitting where a Compose `presentation` module would.

**The chain, end to end, using Login as the worked example:**

```
Compose:  LoginScreen (presentation) ──koinInject()──> LoginViewModel (domain)
React:    LoginCard.tsx ──useLoginFacade()──> LoginFacade (sharedLogic) ──bridgeTo()──> LoginViewModel (domain)
```

Both paths resolve the *same* `LoginViewModel` instance shape from the *same* Koin module
(`sharedDiModules()`) — the only thing that differs is who's asking.

1. **`kmpcore/mvi` (core, reusable — already exists, copy as-is into a new project)** owns the
   generic bridge so no per-feature facade reimplements state/effect/error plumbing:
   - `JsAppGraph` — an interface an app implements once: `ensureStarted()`, `mainDispatcher`,
     `instance(KClass)`. Framework-agnostic; it takes no DI dependency itself.
   - `MviJsBridge<Event, State, Effect>` — wraps one `MVIBaseViewModel`, exposes
     `observeState(map, onState)`, `observeErrors(onError)`, `observeEffects(onEffect)`, `send(event)`,
     `dispose()`.
   - `JsAppGraph.bridgeTo<ViewModel, Event, State, Effect>()` — one-line resolve + wrap:
     `ensureStarted()` then `MviJsBridge(instance(ViewModel::class), mainDispatcher)`.

2. **`sharedLogic` (app-level, one project implements this once)**:
   - `web/core/WebAppGraph.kt` — the app's only `JsAppGraph` implementation. Starts Koin with
     `sharedDiModules()` — the identical module set the Compose apps register — and resolves
     `mainDispatcher` / instances off it.
   - One `FeatureFacade.kt` per feature, `@JsExport`, one per screen the web app needs:
     ```kotlin
     @JsExport
     class LoginFacade {
         private val bridge = WebAppGraph.bridgeTo<
             LoginViewModel, LoginContract.Event, LoginContract.State, LoginContract.Effect,
             >()

         fun observeState(onState: (LoginStateJs) -> Unit): JsSubscription =
             bridge.observeState(LoginContract.State::toJs, onState)
         fun observeErrors(onError: (String) -> Unit): JsSubscription = bridge.observeErrors(onError)
         fun observeEffects(onEffect: (LoginEffectJs) -> Unit): JsSubscription =
             bridge.observeEffects { effect -> effect.toJs()?.let(onEffect) }

         fun requestOtp(identifier: String, rememberMe: Boolean) =
             bridge.send(LoginContract.Event.Submit(LoginForm(identifier, rememberMe)))
         fun dispose() = bridge.dispose()
     }
     ```
   - `FeatureJsModels.kt` next to it — one `toJs()` extension per domain model/state/effect that
     crosses the boundary (same discipline as the DTO layer, just JS instead of the network). An
     `Effect` with a payload becomes a small JS class carrying a `type` string literal plus fields —
     Kotlin sealed classes can't cross `@JsExport`, so match the literal on the TypeScript side by
     hand.

3. **`webApp` (React)**:
   - `shared/useFacade.ts` — the one generic hook, written once, that every `useFeatureFacade.ts`
     wraps: creates the facade inside `useEffect` (not `useMemo` — StrictMode's
     mount→cleanup→mount would dispose it before first render), subscribes state/errors/effects,
     tears everything down (`dispose()`) on unmount.
   - `shared/useLoginFacade.ts` — one hook per feature: constructs the facade, forwards effects to
     the screen (`onOtpRequested`), routes errors to the same toast every screen uses
     (`useFacadeErrorToast`), returns plain state + intent functions.
   - `screens/auth/LoginCard.tsx` — calls `useLoginFacade`, renders from its `state`, calls its
     `requestOtp`. Structured exactly like the Compose split: a thin screen reading facade state,
     "dumb" field components underneath. No `fetch`, no validation, no Koin — same as Compose never
     talking to `NetworkClient` from a screen.

**Rules specific to this chain:**
- Never write a second implementation of a feature's logic for React — if `domain` doesn't expose
  what the screen needs, add it to the ViewModel/use case, not to the facade.
- `domain` never imports Compose (rule 24), which is exactly what lets its `js` target compile and
  makes this whole chain possible — verify `:feature:domain:compileKotlinJs` after any `domain`
  change on a project that has a web app.
- Match fonts, spacing and error handling 1:1 with the Compose app — a web screen must not feel
  like a different product.
- Verifying a flow end-to-end (register → OTP → a form → resume) is cheapest by driving headless
  Chrome over the DevTools protocol against the real dev backend rather than unit-testing each
  layer — keep that script in the scratchpad, not the repo, it's a debugging aid.

---

## Rules

1. **Never put ViewModel logic in Content composables.** Content is pure UI + callbacks.
2. **Always use `rememberSaveable` with a `Saver`** for UI state that must survive rotation.
3. **Use existing design system components first** — check `kmpcore/uidesign/ui/` before building custom.
4. **`ResendCode` manages its own timer** — do not hold timer state in ViewModel or UiModel.
5. **`showInvalidOTP` on OtpInputTextField comes from ViewModel State**, not local state.
6. **Preview calls `FeatureContent`, not `FeatureScreen`** — Content has no DI dependencies.
7. **One `Saver` per UiModel** — only save fields that are user-entered (not derived state).
8. **XML vectors → `PSBVectorImageView`; raster (PNG/WebP) → `PSBImageView`.**
9. **Before creating any UI component, read `kmpcore/uidesign/uidesign.md` and the relevant `<package>/<package>.md`, then check `appcore/src/`.** Only build a custom composable if nothing documented there fits. Reuse is mandatory, not optional. When you change or add a component in `uidesign`, update its package `.md` in the same commit.
10. **Before writing any utility function, search `kmpcore/utils/` first.** Functions like `downTimer`, `getKeyboard`, `EMPTY`, `DispatcherProvider` already exist — duplicating them is a bug.
11. **Any list/grid of data renders through `LazyColumn` / `LazyRow` / `LazyVerticalGrid` with `items(key = …)`** — never `forEach`/`for`/`repeat` emitting composables, never `chunked()` + `Row` as a grid, never a lazy container nested inside another scrollable of the same direction. Iteration is fine only inside a lazy scope to build items.
12. **Colours only from `MaterialThemeColor`** — no hex literals, no `Color.White`/`Color.Black`, no per-feature palette objects. Shade variations via `.copy(alpha = …)` on an existing token. **Colour missing from the theme → stop and ask the user; never invent it or add a token on your own.**
13. **Navigate only through the `com.psbapp.navigation.utils` extensions** (`push` / `replace` / `replaceAll` / `pop` / …) — never raw `navigate()` or `popBackStack()` in a nav graph. See `AuthGraph.kt`.
14. **All user-facing text comes from `strings.xml` via `stringResource(AppStringRes.key)`** — no hardcoded literals in UI code, no text constants in composables or models. Dynamic values go through `%1$s` style placeholders.
15. **Each API call must have its own dedicated use case.** A single use case must never wrap multiple API calls. Enforces single-responsibility at the use-case level — one use case = one API call.
16. **Validation rules live in `domain/validation/` as `Form.validate(onError)` extensions** — the event carries the raw fields, the ViewModel calls the extension and raises the error bar with the `strings.xml` message it names. A Content composable never checks lengths, formats or blanks, and never holds error strings. `CcValidation` and `IAepsEkycValidation` are the references.
17. **Constants live in their own named object under `domain/constant/`** — field lengths and limits in `FeatureLimits`, lazy list keys in `FeatureListKey`, shared response sentinels in `FeatureFlags`; api type/flag values stay next to their url in `remote/Endpoints.kt`. No loose `private const val` in a screen file for anything a request, a rule or a list identity depends on, and no alpha/size value redeclared across files.
18. **The form payload is a domain model** — `domain/model/FeatureForm.kt`, built by `UiModel.toForm()`. Never declare the form `data class` inside the screen file and never assemble it inline at the call site.
19. **Prefill through `UiModel.seed(...)`, never `remember(state.value)`** — seeding only fills fields the user has not typed into; keying a `remember` on state wipes their input and loses it on rotation.
20. **A runtime permission prompt is requested from the screen through an `Effect`** — the ViewModel emits it when the platform call reports a denial; the screen raises the prompt, or points to settings when `isPermanentlyDenied`. No `isGranted` pre-check inside a button's `onClick`.
21. **Never short-circuit a `NetworkClient` flow** with `first` / `firstOrNull` / `take` — it crashes with "Flow exception transparency is violated". Collect to the end.
22. **Check the envelope in `onSuccess`** with `isFailed()` / `failureMessage()` from `appcore/network` — a http 200 can still carry `"status": "failed"`.
23. **Response caching lives in the repository** via `KCache` + a slot in `AppCacheKey` — never cache from a ViewModel, and never invent a second cache mechanism. Bypass it with `useCache = false` or `KCache.isEnabled` while testing rather than by deleting the cache path.
24. **Every feature is exactly two Gradle modules, `domain` and `presentation`** — never a `data` module, never Compose folded into `domain`, never business logic folded into `presentation`. `domain` never imports `androidx.compose.*` — this is what lets it also compile for `js` and back a React web app (see "Web (ReactJS) integration") on projects that have one; on projects that don't, the rule still applies, it just has no web-facing payoff.

---

## Where to Look Before You Build

**Never guess a `kmpcore` API from memory or a similar-sounding name.** Find the module below, open
its `.md`, then drill into the package `.md` it points you to — every function, parameter, default,
usage snippet and gotcha is documented there.

### kmpcore modules

Full index: [`kmpcore/kmpcore.md`](kmpcore.md).

| Module | What it's for |
|---|---|
| [`uidesign`](uidesign/uidesign.md) | Theme, colours, typography, every reusable Compose component (buttons, text, lists, sheets, charts, otp, dialogs, calendar…), MVI base ViewModel |
| [`navigation`](navigation/navigation.md) | Typed nav actions (`push`, `replace`, `pop`, …) and `PSBNavHost` |
| [`networking`](networking/networking.md) | Ktor client, `BaseResponse` envelope, DTO→domain mapping, `onLoading`/`onSuccess`/`onError` collectors |
| [`socket`](socket/socket.md) | Socket.IO — realtime chat/status |
| [`storage`](storage/storage.md) | `datastore` (key–value prefs), `roomdb` (json records), `filemanager` (file pick/save/export) |
| [`localization`](localization/localization.md) | Runtime language switching |
| [`permissions`](permissions/permissions.md) | Runtime permission state (location today) |
| [`kmap`](kmap/kmap.md) | Map view + device location (current/last/live) |
| [`kauth`](kauth/kauth.md) | Google / Apple / Microsoft sign-in buttons and flows |
| [`di`](di/di.md) | Koin bootstrap (`initKoin`, `KoinModulesInterface`) |
| [`resources`](resources/resources.md) | Core drawables/strings shipped inside kmpcore (`CoreDrawableRes`, `CoreStringRes`) |
| [`utils`](utils/utils.md) | Everything non-UI: string/number extensions, dates, json, logging, dispatchers, sharing, device info |
| [`editor`](editor/editor.md) | Text / JSON / Markdown editors — the live implementation |
| [`keditor`](keditor/keditor.md) | Viewport-scoped editor rewrite for very large documents (only the notepad screen uses it so far) |
| [`splitpane`](splitpane/splitpane.md) | Resizable two/three-pane layouts |
| [`encryption`](encryption/encryption.md), [`extension`](extension/extension.md), [`samplelib`](samplelib/samplelib.md), [`gmap`](gmap/gmap.md), [`pdf`](pdf/pdf.md) | Empty / not implemented — see each `.md` for the real alternative |

### App-level (not in `kmpcore`)

| What you need | Where to look |
|---|---|
| App-specific button (`MunafaPlusButton`, `SarthiWhiteButton`) | `appcore/src/commonMain/kotlin/com/psbapp/appcore/ui/button/` |
| Back button / top bar | `appcore/src/commonMain/kotlin/com/psbapp/appcore/ui/topbar/` |
| Drawables / fonts / colors | `appcore/appres/src/commonMain/composeResources/` |
| Drawable resource accessor | `AppDrawableRes.{name}` via `com.psbapp.appres.resource.AppDrawableRes` |
| User-facing text | `appcore/appres/src/commonMain/composeResources/values/strings.xml` — add the key there, read it with `stringResource(AppStringRes.{key})` |
| App theme config | `com.psbapp.appres.theme.getAppThemeConfig` |
| Saved user profile (name, mobile, pan, token…) | `AppPrefDataStore` — `appcore/src/commonMain/kotlin/com/psbapp/appcore/datastore/` |
| Response caching | `KCache` — [`kmpcore/storage/datastore/cache`](storage/datastore/src/commonMain/kotlin/com/psbapp/datastore/cache/cache.md); typed `getCache`/`setCache` (+ `getObject`/`setObject`) in `kmpcore/networking/src/commonMain/kotlin/com/psbapp/networking/cache/`; slot names in `appcore/src/commonMain/kotlin/com/psbapp/appcore/cache/AppCacheKey.kt` |
| `"status": "failed"` inside a 200 | `isFailed()` / `failureMessage()` — `appcore/src/commonMain/kotlin/com/psbapp/appcore/network/` |
| Leaving the app (WhatsApp, share sheet, dialer, mail) | `ExternalAppLauncher` — `appcore/src/commonMain/kotlin/com/psbapp/appcore/launcher/` (`kmpcore`'s `SharingManager` stubs these out) |
| Copy to clipboard | `LocalClipboard.current` + `Clipboard.setText` from `uidesign/utils` — `LocalClipboardManager` is deprecated |
| Reference: two-module feature (domain + presentation) | `appfeatures/auth/login`, `appfeatures/auth/otp` |
| Reference: React facade for a feature *(only if the project has a ReactJS web app — see "Web (ReactJS) integration")* | `sharedLogic/src/jsMain/.../web/auth/LoginFacade.kt` + `webApp/src/shared/useLoginFacade.ts` |
