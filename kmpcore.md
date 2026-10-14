# kmpcore — Module Index

Every reusable KMP library the apps are built on. `kmpcore/` is **frozen without explicit
approval** — see `architecture.md` STRICT RULES. Read a module's own `.md` before writing code that
touches it; do not guess an API from memory or from a similar-sounding function name.

> **How to use this file.** Find the row that matches what you're building, open that module's `.md`,
> then drill into the package `.md` it points you to. Every module doc follows the same shape:
> purpose → "I need to…" table → package docs → gotchas → see also.

---

## UI

| Module | Use it for |
|---|---|
| [`uidesign`](uidesign/uidesign.md) | Theme, colours, typography, every reusable Compose component (buttons, text, lists, sheets, charts, …), MVI base ViewModel |
| [`navigation`](navigation/navigation.md) | Typed nav actions (`push`, `replace`, `pop`, …) and `PSBNavHost` |
| [`editor`](editor/editor.md) | Text / JSON / Markdown editors — **use this one**, it's the live implementation |
| [`keditor`](keditor/keditor.md) | Viewport-scoped editor rewrite for very large documents — only the notepad screen uses it so far |
| [`splitpane`](splitpane/splitpane.md) | Resizable two/three-pane layouts (vendored, extended to mobile) |

## Data & networking

| Module | Use it for |
|---|---|
| [`networking`](networking/networking.md) | Ktor client, `BaseResponse` envelope, DTO→domain mapping, `onLoading`/`onSuccess`/`onError` collectors |
| [`socket`](socket/socket.md) | Socket.IO — realtime chat/status |
| [`storage`](storage/storage.md) | `datastore` (key–value prefs), `roomdb` (json records), `filemanager` (file pick/save/export) |
| [`localization`](localization/localization.md) | Runtime language switching |

## Platform & auth

| Module | Use it for |
|---|---|
| [`permissions`](permissions/permissions.md) | Runtime permission state (location today) |
| [`kmap`](kmap/kmap.md) | Map view + device location (current/last/live) |
| [`kauth`](kauth/kauth.md) | Google / Apple / Microsoft sign-in buttons and flows |
| [`imagepicker`](imagepicker/imagepicker.md) | Single-image gallery picker (Android/iOS/desktop/wasm) + base64 encode |
| [`di`](di/di.md) | Koin bootstrap (`initKoin`, `KoinModulesInterface`) |
| [`resources`](resources/resources.md) | Core drawables/strings shipped inside kmpcore (`CoreDrawableRes`, `CoreStringRes`) |

## Shared code

| Module | Use it for |
|---|---|
| [`utils`](utils/utils.md) | Everything non-UI: string/number extensions, dates, json, logging, dispatchers, sharing, device info |

## Empty / placeholder

| Module | Status |
|---|---|
| [`encryption`](encryption/encryption.md) | Not implemented — template files only |
| [`extension`](extension/extension.md) | Not implemented — template files only |
| [`samplelib`](samplelib/samplelib.md) | Empty scaffold for new libraries |
| [`gmap`](gmap/gmap.md) | Empty — use `kmap` |
| [`pdf`](pdf/pdf.md) | Empty — no PDF support yet |

---

## Picking the right module — quick answers

| Need | Module |
|---|---|
| Any composable, colour, text style, spacing | `uidesign` |
| Navigate between screens | `navigation` |
| Call an API | `networking` |
| Store a token / flag / small setting | `storage/datastore` |
| Store a list of records offline | `storage/roomdb` |
| Let the user open/save/export a file | `storage/filemanager` |
| Realtime chat / live status | `socket` |
| Ask for a permission | `permissions` |
| Show a map or get device location | `kmap` |
| Social sign-in button | `kauth` |
| Let the user pick a gallery image and send it as base64 | `imagepicker` |
| Any non-UI helper (dates, json, strings, logging) | `utils` |
| Text/JSON/Markdown editor | `editor` (use `keditor` only for very large documents) |

---

## Rules that apply across every module

From `architecture.md` — see it for the full text:

1. `kmpcore/` is frozen without explicit approval — ask before changing anything here.
2. Before building UI, read `uidesign/uidesign.md` and the relevant package `.md`.
3. Before writing a utility, read `utils/utils.md` — duplicating an existing helper is a bug.
4. Colours only from `MaterialThemeColor`; missing colour → stop and ask.
5. Collections render lazily (`items(key = …)`), never `forEach` emitting composables.
6. Navigate only through `navigation/utils` extensions, never raw `NavController` calls.
7. All user-facing text from `strings.xml` via `stringResource`.

---

## Maintaining these docs

- Change any public API in `kmpcore` → update that package's `.md` in the same commit.
- New module → create `<module>/<module>.md` following the existing pattern and add a row above.
- New package inside an existing module → add its `.md` and a row in that module's index.
