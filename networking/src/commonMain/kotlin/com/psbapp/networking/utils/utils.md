# Package: `networking/utils`

Collectors, headers and configuration used around a call.

| I need to… | Use |
|---|---|
| React to a call in a ViewModel | [`onLoading` / `onSuccess` / `onError`](#collectors) |
| The same, without a flow | `onSuccessSync`, `onFailureSync`, `onLoadingSync` |
| Loader style for a call | [`BaseProgressBarState`](#baseprogressbarstate) |
| Standard headers | `HeadersBuilder.apiHeaders { }` |
| Shared json / logging / retry config | `getJsonConfig()`, `LoggingConfig.networkLogger()`, `HttpRequestRetryConfig.defaultHttpRetryConfigs()` |
| Check an http status | `checkStatus(status)` |
| Object → form/query map | `T.toRequestMapper(...)`, `JsonElement?.isJsonNull()` |
| Why the user was logged out | `LogoutType` |
| Error codes / keys | `KtorConstants` |

---

## Collectors

```kotlin
viewModelScope.launch(dispatcher.io) {
    useCase()
        .onLoading { setState { copy(loading = it) } }
        .onSuccess { data -> setState { copy(items = data.items) } }
        .onError { showErrorMsgBar(it) }
        .collect()
}
```

They return the same flow, so they chain; the terminal `.collect()` is required.

## `BaseProgressBarState`

`ButtonLoading`, `ScreenLoading`, `FullScreenLoading`, `ShimmerLoading` — carried by
`NetworkState.Loading`. `NetworkClient` emits `ShimmerLoading` by default.

## `LogoutType`

`USER_GENERATED`, `SYSTEM_GENERATED`, `ACCOUNT_DELETION`.

## `KtorConstants`

`NetworkKey` (`X_API_KEY`, `X_APP_VERSION`) and `NetworkErrorCodes`
(`ACCESS_TOKEN_EXPIRED = 401`, `REFRESH_TOKEN_EXPIRED = 403`, `INTERNET_NOT_WORKING = 712`,
`UNKNOWN_ERROR_OCCURRED = 713`).

> ⚠ `X_API_KEY` is a **hardcoded secret committed to the repo**. It should move to a build-time
> config/secret store; treat it as compromised until it does.

---

## See also

- [`networking/client`](../client/client.md), `uidesign/ui/toastbar` (`showErrorMsgBar`)
