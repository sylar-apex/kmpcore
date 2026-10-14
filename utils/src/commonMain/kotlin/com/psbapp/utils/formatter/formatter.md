# Package: `utils/formatter`

## `Float.format(status: String): String`

Formats a number for display against a status/label (used by dashboard tiles).

`Float.formatToThousandsMillionsBillions()` (internal) compacts large values to `1.2K` / `3.4M` style.

**Gotcha:** currency amounts use `formatAmount(Int)` in [`utils`](../utils.md), not this package.

---

## See also

- [`utils`](../utils.md) → `formatAmount`, `Double.round`, `Double.clean`
