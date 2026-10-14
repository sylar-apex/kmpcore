# Package: `utils/uuid`

## `getUUID(): String`

Random UUID for request ids, local record keys, idempotency keys.

**Gotcha:** not stable across launches — for a device id use `AppInfoUtils.getDeviceUUID()` in
[`utils/appinfo`](../appinfo/appinfo.md).
