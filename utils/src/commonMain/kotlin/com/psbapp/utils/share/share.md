# Package: `utils/share`

Sharing out of the app: browser, WhatsApp, dialer, other apps, and receipt images.

## `SharingManager`

| member | signature | notes |
|---|---|---|
| `openBrowser` | `@Composable (url: String)` | opens the platform browser |
| `whatsAppShare` | `@Composable (message: String)` | |
| `openOtherApp` | `(packageName: String)` | |
| `openPhoneDialer` | `(number: String)` | |

Injected via `sharingModule()` (registered in the app's Koin setup); the implementation comes from
`SharingFactory` per platform.

## Receipts

- `ReceiptShareHelper` — platform object that shares a rendered receipt image.
- `ImageBitmap.toPngByteArray()` — encodes a captured composable for sharing.

**Gotchas**
- `openBrowser` and `whatsAppShare` are `@Composable` — call them from composition (e.g. behind a
  flag), not from a ViewModel.
- `openOtherApp` silently does nothing when the target app is missing on some platforms.

---

## See also

- [`utils/clipboard`](../clipboard/clipboard.md) — copy instead of share
