# Package: `utils/pinchzoom`

Pinch/double-tap zoom for images and documents.

| API | signature |
|---|---|
| `Modifier.zoomable` | `(state: PinchZoomState, …)` — attaches gestures |
| `rememberZoomState` | `@Composable (…) : PinchZoomState` |
| `PinchZoomState` | scale/offset holder |
| `PinchZoomState.toggleScale` | `suspend (…)` — double-tap between fit and zoomed |

```kotlin
val zoomState = rememberZoomState()
PSBImageView(modifier = Modifier.zoomable(zoomState), image = …)
```

**Gotchas**
- `uidesign/utils/modifier` has its own `Modifier.zoomable`; this one is state-driven — do not import
  both in one file.
- The zoom container needs a clipped, bounded size or content escapes its bounds.

---

## See also

- `uidesign/ui/imageview/imageview.md`
