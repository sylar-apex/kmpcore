# Package: `networking/client`

The HTTP layer. Feature `ApiServiceImpl` classes call `NetworkClient`; nothing else touches Ktor
directly.

| I need to… | Use |
|---|---|
| POST returning a flow | `ktorPostFlowRequest<T>(body, url, …)` |
| PUT / DELETE returning a flow | `ktorPutFlowRequest<T>`, `ktorDeleteFlowRequest<T>` |
| One-shot POST (no flow) | `ktorPostRequest<T>(…)` |
| Upload a file | Prefer `ktorUploadFlowRequest<T>` / `ktorUploadListFlowRequest<T>` in [`upload/`](../upload/upload.md) (real content type, list support). Legacy: `ktorUploadFileFlowRequest<T>`, `ktorUploadFileByteArrayFlowRequest<T>` |
| Any method, full control | `ktorApiRequest<T>(method, url, …)` |
| Represent a call's state | [`NetworkState`](#networkstate) |

---

## `NetworkClient`

Wraps `KtorClient`. Every flow-returning function emits `Loading(ShimmerLoading)` →
`Success` | `Error` → `Loading(Idle)` and returns `Flow<NetworkState<BaseResponse<T>>>`.

**The trailing `Loading(Idle)` is emitted on the failure path too**, so a collector's
`onLoading { }` always flips back to `false`. It used to be missing from the `catch` blocks, which
left a screen's `loading` state stuck `true` after any timeout or parse failure — and
`ViewScreen`'s loading overlay swallows every pointer event, so the whole screen went dead until
the app was restarted.

```kotlin
class RechargeApiServiceImpl(private val ktorClient: NetworkClient) : RechargeApiService {
    override suspend fun getProviders(
        request: RechargeProvidersRequest
    ): Flow<NetworkState<BaseResponse<List<RechargeProviderResponse>>>> =
        ktorClient.ktorPostFlowRequest(body = request, url = RECHARGE_PROVIDERS)
}
```

Common parameters: `body: Any`, `url: String`, `queryParams: Map<String, String> = emptyMap()`,
`headers: Map<String, String> = emptyMap()`, `showApiLoader: Boolean = true`.

**Gotchas**
- These functions are `inline` + `reified`; a module compiled at a lower JVM target cannot call them
  (wrap in a non-inline helper in that module).
- `showApiLoader = false` skips the `Loading` emission — use it for background refreshes.
- Serialization failures surface as `NetworkState.Error("Serialization Error: …")`, not as a crash.

## `KtorClient`

Owns the configured `HttpClient` (engine from `getHttpClientEngine()`, json config, logging, retry,
auth header). Injected through `networkModule()`. Features never construct it.

## `NetworkState<T>`

| case | payload |
|---|---|
| `Loading` | `progressBarState: BaseProgressBarState` |
| `Success` | `response: T` |
| `Error` | `message: String` |

`NetworkState<T>.mapResponse { }` transforms the success value.

## Request builders

`HttpRequestBuilder.GET/POST/PUT/DELETE`, `POSTFormData`, `POSTMultiPartFormData`, plus the
`GET/POST/PUT/DELETE/PATCH/HEAD/OPTIONS` method constants — used inside `NetworkClient`, rarely
needed directly.

---

## See also

- [`networking/utils`](../utils/utils.md) — `onSuccess` / `onError` / `onLoading` collectors
- [`networking/mapper`](../mapper/mapper.md) — DTO → domain
- [`networking/response`](../response/response.md) — `BaseResponse` envelope
