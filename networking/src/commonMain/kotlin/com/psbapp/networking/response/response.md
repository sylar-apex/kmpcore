# Package: `networking/response`

## `BaseResponse<T>`

The envelope every endpoint returns:

```json
{ "status": "success", "message": "…", "data": { … } }
```

| field | notes |
|---|---|
| `status` | also accepts `success` (`@JsonNames`) |
| `message` | also accepts `error`, `error_message` |
| `data` | `T?` — the real payload |

Domain models extend it:

```kotlin
data class RechargeProvidersData(
    val providers: List<RechargeProvider>,
    val responseStatus: String,
    val responseMessage: String,
) : BaseResponse<RechargeProvidersData>(status = responseStatus, message = responseMessage)
```

**Gotcha:** an endpoint that returns fields flat next to `status`/`message` (no `data` wrapper) is a
**backend bug** — flag it, do not add a special-case DTO (`architecture.md`).

---

## See also

- [`networking/mapper`](../mapper/mapper.md), [`networking/client`](../client/client.md)
