# Package: `utils/clipboard`

## `suspend fun ClipEntry.readText(): String?`

Reads text from a clipboard entry through the platform clipboard API.

Writing to the clipboard uses `uidesign/utils`' `Clipboard.setText(text)`; this package only bridges
reading a `ClipEntry`.

**Gotcha:** returns `null` when the entry holds no text (image, file, empty clipboard).

---

## See also

- `uidesign/utils/utils.md` → `Clipboard.getText()` / `Clipboard.setText()`
