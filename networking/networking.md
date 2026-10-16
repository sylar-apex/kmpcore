# Module: `networking` — HTTP & API Index

Ktor client, the `{status, message, data}` response envelope, DTO→domain mapping and the flow
collectors every ViewModel uses. Feature modules touch this through their own
`remote/ApiServiceImpl` — never through raw Ktor.

> **How to use this file.**
> This is only an index. Find the row that matches what you are doing, then **open that package's
> `.md`** for the full API, defaults, usage snippet and gotchas.

Docs live next to the code: `src/commonMain/kotlin/com/psbapp/networking/<package>/<package>.md`.

---

## Call flow

```
ApiService (feature)  →  NetworkClient.ktorPostFlowRequest<Dto>()   → Flow<NetworkState<BaseResponse<Dto>>>
Repository (feature)  →  .mapApiData { mapper.dtoToDomain(it) }     → Flow<NetworkState<Domain>>
ViewModel  (feature)  →  .onLoading { } .onSuccess { } .onError { } .collect()
```

---

## Package docs

| Doc | Read it when you need |
|---|---|
| [`client/client.md`](src/commonMain/kotlin/com/psbapp/networking/client/client.md) | Making calls: `ktorPostFlowRequest`, PUT/DELETE/upload variants, `NetworkState`, `KtorClient` |
| [`upload/upload.md`](src/commonMain/kotlin/com/psbapp/networking/upload/upload.md) | Generic multipart upload: one file or a list, typed `T` response |
| [`response/response.md`](src/commonMain/kotlin/com/psbapp/networking/response/response.md) | The `BaseResponse<T>` envelope every endpoint must return |
| [`request/request.md`](src/commonMain/kotlin/com/psbapp/networking/request/request.md) | Shared request scaffolding (`BaseRequest`) |
| [`mapper/mapper.md`](src/commonMain/kotlin/com/psbapp/networking/mapper/mapper.md) | `Mapper` base class, `mapApiData` / `mapApiDataSync` |
| [`utils/utils.md`](src/commonMain/kotlin/com/psbapp/networking/utils/utils.md) | `onLoading` / `onSuccess` / `onError` collectors, headers, retry & json config, error codes, `LogoutType` |
| [`datastore/datastore.md`](src/commonMain/kotlin/com/psbapp/networking/datastore/datastore.md) | `NetworkInfo` — token, base url, api key the app supplies |
| [`di/di.md`](src/commonMain/kotlin/com/psbapp/networking/di/di.md) | `networkModule()` Koin bindings |

---

## Not in this module

| Need | Where |
|---|---|
| Endpoint urls, DTOs, repositories, use cases | the feature module (`appfeatures/**/remote`, `/domain`) |
| Base url constant | `appcore/network/Endpoints` |
| Socket connections | [`socket`](../socket/socket.md) |
| Token storage | `storage/datastore` |
| Showing an error to the user | `uidesign/ui/toastbar` |

---

## Maintaining these docs

- Change a client function, envelope field or collector → update its package `.md` in the same commit.
