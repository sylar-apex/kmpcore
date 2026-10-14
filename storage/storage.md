# Module: `storage` — Persistence Index

Everything the app stores on device. Three independent sub-modules; include only the one you need.

> **How to use this file.** Index only — open the sub-module's `.md`, then its package `.md`, for the
> full API and gotchas.

| Sub-module | Use it for | Doc |
|---|---|---|
| `datastore` | Key–value preferences: tokens, user id, flags, small settings | [`datastore/datastore.md`](datastore/datastore.md) |
| `roomdb` | Local database — json records by id, offline cache, queues | [`roomdb/roomdb.md`](roomdb/roomdb.md) |
| `filemanager` | Picking, saving and exporting files (desktop/web tooling) | [`filemanager/filemanager.md`](filemanager/filemanager.md) |

---

## Choosing

| Need | Use |
|---|---|
| Auth token, user id, "seen onboarding" flag | `datastore` |
| A list of records to read/update/delete offline | `roomdb` |
| Let the user open or export a file | `filemanager` |
| Cache an api response by key, with a ttl | `datastore` → [`KCache`](datastore/src/commonMain/kotlin/com/psbapp/datastore/cache/cache.md) |
| Cache API images | nothing here — Coil handles it (`uidesign/ui/imageview`) |

**Never store tokens or PII in `roomdb` or a file** — preferences storage is the only place with
platform-backed protection, and even there avoid raw secrets (see `encryption`).

---

## Maintaining these docs

- Change an API → update that sub-module's package `.md` in the same commit.
