# Package: `networking/datastore`

## `NetworkInfo`

Interface the app implements so the core client can build requests without knowing app storage.

| member | returns |
|---|---|
| `getAccessToken()` | `suspend String` — bearer token |
| `getBaseUrl()` | `String` |
| `getSocketHostUrl()` / `getSocketPort()` | socket endpoint |
| `getXApiKey()` | `String` |
| `getAppVersion()` | `String` |

Bound in the app's Koin setup (`networkInfoModule()` in `appcore`).

**Gotcha:** `getAccessToken()` is `suspend` and read per request — returning a stale token here logs
users out with 401s.

---

## See also

- [`networking/client`](../client/client.md), `storage/datastore`
