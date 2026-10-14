# Package: `utils` (root)

Top-level helpers that do not belong to a sub-package. Sub-packages have their own docs — see the
module index [`utils/utils.md`](../../../../../../utils.md).

| I need to… | Use |
|---|---|
| Empty / null-safe defaults | [String & number extensions](#string--number-extensions) |
| Format a rupee amount | `formatAmount(amount: Int)` |
| Countdown timer | `downTimer(totalTime, delayTimeInSecond = 1): Flow<Int>` |
| Hide/show the soft keyboard | `getKeyboard()` |
| Launch a coroutine tersely | `CoroutineScope.coroutineLaunch { }`, `getCoroutineScope()` |
| Platform identity | `getPlatform()`, `Platform`, `PlatformType` |

---

## String & number extensions

| API | notes |
|---|---|
| `String.EMPTY`, `Int.EMPTY`, `Long.EMPTY`, `Float.EMPTY`, `Double.EMPTY`, `Boolean.EMPTY` | canonical empty values |
| `String?.value`, `Int?.value`, … | null-safe unwrap to the `EMPTY` default |
| `String?.valueNa` | `"N/A"` when null |
| `String.Companion.COMMA` | `","` |
| `Double.round(decimals = 2)`, `Float.round(decimals = 2)` | rounding |
| `Double.clean()`, `Float.clean()` | drops trailing `.0` |
| `String.cleanNumber()` | strips non-numeric characters |
| `String?.containsIgnoreCase(other)`, `String.equalIgnore(str)` | case-insensitive compare |
| `String.isDigitsOnly()` | phone/OTP checks |
| `String?.capitalizeFirstChar()`, `String.capitalizeWords()` | casing |
| `String.getFirstName()`, `String.getLastName()` | split a full name |
| `String.isValidName()` | name validation |
| `String.addPrefixZero()` | pads to 2 chars (`"7"` → `"07"`) |
| `String.getSubString(maxLength)` | safe truncate |
| `String.splitStringIntoChunks(chunkSize = 20)` | chunking |
| `concatenateStrings(first, second, third)` | join three parts |

## `formatAmount(amount: Int): String`

Indian grouping plus a trailing suffix: `500` → `"500.00 /-"`, `123456` → `"1,23,456.00 /-"`.

**Gotcha:** the `" /-"` suffix is part of the output. For a `₹` prefix strip it first:
`"₹" + formatAmount(v).substringBefore(" /-")`.

## `downTimer(totalTime: Int, delayTimeInSecond: Int = 1): Flow<Int>`

Emits a countdown once per `delayTimeInSecond`. `ResendCode` in `uidesign` already uses it — do not
re-implement resend timers.

## `getKeyboard(): SoftwareKeyboardController?`

```kotlin
val keyboard = getKeyboard()
keyboard?.hide()
```

## Coroutines & platform

- `CoroutineScope.coroutineLaunch { }` — launch a suspend block.
- `getCoroutineScope()` — `rememberCoroutineScope()` alias for composables.
- `getPlatform(): Platform`, `PlatformType` — platform identity. For UI-side platform checks prefer
  `uidesign/utils` (`getPlatformType`, `isMobilePlatform`, …).

**Gotcha:** dispatchers come from [`utils/dispatcher`](dispatcher/dispatcher.md) — never hardcode
`Dispatchers.IO`.
