# Package: `utils/logger`

App logging. **Never use `println`** — `Print` routes to the platform logger (Logcat / NSLog / console).

## `Print`

| function | signature |
|---|---|
| `log` | `(message: String?, tag: String = "AppLog => ")` — INFO |
| `logInfo` | `(message: String?, tag: String = …)` |
| `logWarn` | `(message: String?, tag: String = …)` |
| `logError` | `(message: String?, tag: String = …, throwable: Throwable? = null)` |
| `logDebug` / `logVerbose` / `logNetwork` | same shape, different `LogPriority` |

`LogPriority` — `VERBOSE`, `DEBUG`, `INFO`, `NETWORK`, `WARN`, `ERROR`.
Long messages are split into 1000-char chunks so nothing is truncated by the platform logger.

```kotlin
Print.log("PSBNetworkImage: $url")
Print.logError("payment failed", throwable = e)
```

**Gotchas**
- `platformLog` is `internal expect` — call `Print`, not the platform function.
- Logs are not stripped in release builds; do not log tokens, OTPs, card numbers or full API payloads.

---

## See also

- [`networking`](../../../../../../../../networking/networking.md) — request/response logging
