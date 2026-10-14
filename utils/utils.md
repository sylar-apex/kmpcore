# Module: `utils` — Shared Utilities Index

Platform-agnostic helpers used by every module: string/number extensions, dates, json, logging,
dispatchers, sharing, device info. **No UI in here** — Compose-side helpers live in
`uidesign/utils`.

> **How to use this file.**
> This is only an index. Find the row that matches what you need, then **open that package's `.md`**
> for the full API, defaults, usage snippet and gotchas.
> Before writing any helper function, check here first — duplicating one of these is a bug
> (`architecture.md` rule 10).

Docs live next to the code: `src/commonMain/kotlin/com/psbapp/utils/<package>/<package>.md`.

---

## Package docs

| Doc | Read it when you need |
|---|---|
| [`utils/utils.md`](src/commonMain/kotlin/com/psbapp/utils/utils.md) | Root helpers: `String.EMPTY` and null-safe `value`, string/number extensions, `formatAmount`, `downTimer`, `getKeyboard`, coroutine and platform helpers |
| [`logger/logger.md`](src/commonMain/kotlin/com/psbapp/utils/logger/logger.md) | Logging — `Print.log` / `logError` / `logNetwork` instead of `println` |
| [`dispatcher/dispatcher.md`](src/commonMain/kotlin/com/psbapp/utils/dispatcher/dispatcher.md) | `DispatcherProvider` (`io` / `main` / `default`) injected into every ViewModel |
| [`datetime/datetime.md`](src/commonMain/kotlin/com/psbapp/utils/datetime/datetime.md) | Date formatting/parsing, `DateTimeFormat` patterns, `LocalDate` plus/minus helpers |
| [`json/json.md`](src/commonMain/kotlin/com/psbapp/utils/json/json.md) | Json encode/decode (`KsonUtil`), json ↔ map conversions |
| [`share/share.md`](src/commonMain/kotlin/com/psbapp/utils/share/share.md) | Share out of the app: browser, WhatsApp, dialer, other apps, receipt images |
| [`clipboard/clipboard.md`](src/commonMain/kotlin/com/psbapp/utils/clipboard/clipboard.md) | Reading clipboard text |
| [`appinfo/appinfo.md`](src/commonMain/kotlin/com/psbapp/utils/appinfo/appinfo.md) | App version, device model, OS, carrier, device UUID |
| [`pinchzoom/pinchzoom.md`](src/commonMain/kotlin/com/psbapp/utils/pinchzoom/pinchzoom.md) | Pinch / double-tap zoom for images and documents |
| [`formatter/formatter.md`](src/commonMain/kotlin/com/psbapp/utils/formatter/formatter.md) | Compact number formatting (`1.2K`, `3.4M`) |
| [`compare/compare.md`](src/commonMain/kotlin/com/psbapp/utils/compare/compare.md) | `equalTo` helpers for lists, strings and generics |
| [`uuid/uuid.md`](src/commonMain/kotlin/com/psbapp/utils/uuid/uuid.md) | Random UUID generation |
| [`ripple/ripple.md`](src/commonMain/kotlin/com/psbapp/utils/ripple/ripple.md) | `NoRippleInteractionSource` — Material component without a ripple |
| [`domain/domain.md`](src/commonMain/kotlin/com/psbapp/utils/domain/domain.md) | Shared domain types (`StringFormat`) |

---

## Not in this module

| Need | Where |
|---|---|
| Compose modifiers, responsive helpers, platform/window checks | `uidesign/utils` |
| Colours, typography, dimensions | `uidesign/theme` |
| Http client, `NetworkState`, `BaseResponse` | `networking` |
| Datastore / Room / file storage | `storage/*` |

---

## Maintaining these docs

- Change a helper → update its package `.md` in the same commit.
- New package → create `<package>/<package>.md` and add a row above.
