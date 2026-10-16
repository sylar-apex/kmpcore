# upload

Generic multipart image/file upload on `NetworkClient`. Sends the real file name and content type
(unlike the older `ktorUploadFileFlowRequest`, which stamps every file as jpeg).

| Function | Returns | Use |
|---|---|---|
| `ktorUploadFlowRequest<T>(url, file, fieldName = "file", fields = emptyMap(), onProgress)` | `Flow<NetworkState<BaseResponse<T>>>` | One file → the envelope with `data: T` |
| `ktorUploadListFlowRequest<T>(url, files, fieldName, fields, onProgress)` | `Flow<NetworkState<List<T>>>` | Several files, one request each, in order; stops at the first failure |

`UploadFile(bytes, fileName, contentType)` is the part. `T` is your `@Serializable` response DTO.

```kotlin
// ApiServiceImpl
override suspend fun uploadLogo(file: UploadFile) =
    client.ktorUploadFlowRequest<MediaUploadResponse>(url = Endpoints.media(MediaKind.LOGO), file = file)

override suspend fun uploadGallery(files: List<UploadFile>) =
    client.ktorUploadListFlowRequest<MediaUploadResponse>(url = Endpoints.MEDIA_UPLOAD, files = files,
        fields = mapOf("folder" to "gallery"))
```

Gotchas
- The list helper emits `Success` only when every file went up; a `data: null` envelope counts as a failure.
- A 2xx with `"status": "failed"` still comes back as `Success` from the single helper — check `isFailed()` in `onSuccess`.
- Never `first()` the flow (architecture rule 21) — collect to the end.
