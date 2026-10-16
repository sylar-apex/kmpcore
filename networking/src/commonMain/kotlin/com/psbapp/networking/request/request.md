# Package: `networking/request`

`BaseRequest` — shared request scaffolding for endpoints that need common fields.

Feature requests are plain `@Serializable data class`es in the feature's `remote/dto/request/`; use
this only when an endpoint family shares a base shape.

---

## See also

- [`networking/utils`](../utils/utils.md) → `toRequestMapper` for form-style bodies
