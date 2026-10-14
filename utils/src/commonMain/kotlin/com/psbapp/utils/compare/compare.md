# Package: `utils/compare`

Equality helpers that read better than manual checks.

| function | notes |
|---|---|
| `List<T>.equalTo(second: List<T>, isCheckOrder: Boolean = false)` | order-insensitive by default |
| `String.equalTo(second: String, ignoreCase: Boolean = true)` | case-insensitive by default |
| `T.equalTo(second: T)` | generic structural compare |

**Gotcha:** the defaults are lenient (order ignored, case ignored) — pass the flags when you need a
strict comparison.
