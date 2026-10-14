# Package: `utils/datetime`

Date/time formatting and arithmetic, written by hand so every platform accepts the same patterns —
JVM and JS date libraries disagree on pattern letters, and the mismatch used to fail silently on web.

| I need to… | Use |
|---|---|
| Reformat a date string | `dateTimeFormatter(dateTime, currentFormat, outFormat)` |
| Today in a pattern | `getCurrentDate(pattern = DateTimeFormat.yyyy_MM_dd.value)` |
| A ready-made pattern | `DateTimeFormat.<name>.value` |
| Detect which format a string is in | `findDateTimeFormatFinder(dateTime)` |
| Add/subtract days, months, years | `LocalDate.plusDays/minusDays/plusMonths/minusMonths/plusYears/minusYears` |
| Format/parse manually | `DatePattern` |

```kotlin
val shown = dateTimeFormatter(
    dateTime = apiDate,
    currentFormat = DateTimeFormat.yyyy_MM_dd.value,
    outFormat = DateTimeFormat.dd_MMM_yyyy.value
)
```

`DateTimeFormat` covers dash / slash / dot separators, 12- and 24-hour times, and combined
date-time patterns (`dd_MM_yyyy`, `yyyy_MM_dd_slash`, `hh_mm_A`, …) — read the enum for the full list.

Supported pattern letters: `yyyy yy MMMM MMM MM M dd d EEEE EEE HH H hh h mm m ss s a`, plus
`'quoted'` literals; anything else is treated as a literal.

**Gotchas**
- `dateTimeFormatter` returns an **empty string** when the input does not match `currentFormat` —
  check before displaying.
- Always pass patterns from `DateTimeFormat`, not raw strings.

---

## See also

- `uidesign/ui/datetimepicker`, `uidesign/ui/calendar` — pickers and calendars
