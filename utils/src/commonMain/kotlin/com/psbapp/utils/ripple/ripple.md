# Package: `utils/ripple`

## `NoRippleInteractionSource`

`MutableInteractionSource` that swallows press interactions, so a Material component draws no ripple.

```kotlin
Button(interactionSource = remember { NoRippleInteractionSource() }, …)
```

**Gotcha:** for plain clickables prefer `uidesign/utils/modifier`'s `Modifier.onClick` — it is already
ripple-free (`showRipple = false` by default).
