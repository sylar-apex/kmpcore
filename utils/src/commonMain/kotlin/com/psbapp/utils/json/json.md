# Package: `utils/json`

JSON encode/decode on one shared `Json` configuration (`explicitNulls = false`, `ignoreUnknownKeys`,
`encodeDefaults`, `isLenient`, `prettyPrint`).

| I need to… | Use |
|---|---|
| Object → json string | `KsonUtil.toJson(obj)` |
| Json string → object | `KsonUtil.fromJson<T>(json)` |
| Json ↔ `JsonElement` | `KsonUtil` `toJsonElement()` / `toObject()` |
| Json string → map | `KsonUtil` `toMapOfStrings()` / `toMapOfJsonElements()` |
| Map → object | `KsonUtil.mapToObject<T>(map)` |
| `Map<String, String>` → request body | `map.toJsonBody()` → `JsonBody` |

```kotlin
val json = KsonUtil.toJson(subServices)              // "" on failure
val list = KsonUtil.fromJson<List<SubService>>(json) // null on failure
```

**Gotchas**
- `KsonUtil.toJson` returns `String.EMPTY` on failure and `fromJson` returns `null` — check the result.
- These functions are `inline`, so they cannot be called from a module compiled at a lower JVM target
  (e.g. `shared` at JVM 11 calling into a JVM 21 module). Wrap them in a non-inline helper in the
  feature module instead.
- `toJsonBody()` is for endpoints whose key names are only known at runtime (e.g. BBPS bill pay sends
  the biller's own inputs as `number0`, `number1`, …), so no data class can name them. Pass the
  resulting `JsonBody` straight to `NetworkClient` — its `body` is `Any`.
- Do **not** pass a raw `Map` or a bare `JsonObject` as a request body. `NetworkClient.body` is `Any`,
  so Ktor has no static type and guesses a serializer from the runtime class: a `Map` fails with
  `Serializer for class 'LinkedHashMap' is not found`, and a `JsonObject` (which *is* a `Map`) fails on
  its values with `Serializer for class 'JsonLiteral' is not found`. `JsonBody` exists to give the
  guess a real `@Serializable` class to land on.
- The shared config is the top-level `JSON` in `KsonUtil.kt`; `KsonUtil.json` is an alias for it.

---

## See also

- [`networking`](../../../../../../../../networking/networking.md) — DTO serialization
