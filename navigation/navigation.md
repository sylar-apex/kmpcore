# Module: `navigation` — Navigation Index

Typed Navigation-Compose wrappers. **Nav graphs must use these extensions — never raw
`navigate()` / `popBackStack()`** (`architecture.md` rule 12).

> Index only — open the package `.md` for the full API and gotchas.

| Doc | Read it when you need |
|---|---|
| [`navigation/utils/utils.md`](src/commonMain/kotlin/com/psbapp/navigation/utils/utils.md) | `push` / `replace` / `replaceAll` / `pop` / … , `PSBNavHost`, `LocalNavHostController` |

---

## Not in this module

| Need | Where |
|---|---|
| Route definitions and graphs | the app (`shared/navigation/routes`, `.../graph`) |
| Bottom navigation UI | `uidesign/ui/bottomnav` |

---

## Maintaining these docs

- Add or change an extension → update the package `.md` in the same commit.
