# Package: `utils/appinfo`

Device and build metadata, mostly for API headers and support screens.

## `AppInfoUtils` (platform object)

`getAppVersionName()`, `getAppVersionCode()`, `getDeviceManufacturer()`, `getDeviceModel()`,
`getOSVersion()`, `getPackageName()`, `getNetwork()`, `getNetworkCarrier()`, `getDeviceUUID()`,
`killAppProcess()`.

`BuildConfigProvider` — interface the app implements so core code can read build flags without an
Android dependency.

**Gotchas**
- Values are best-effort per platform; network/carrier can come back empty on iOS and web.
- `killAppProcess()` is Android-only in practice — do not use it for normal logout.

---

## See also

- `uidesign/ui/textview` → `PSBAppVersion` renders the version
