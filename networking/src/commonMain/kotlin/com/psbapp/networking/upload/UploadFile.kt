package com.psbapp.networking.upload

/**
 * One file to send as a multipart part. [contentType] and [fileName] go up as-is so the server can
 * pick the right extension (the older `ktorUploadFileFlowRequest` stamps everything as jpeg).
 */
class UploadFile(
    val bytes: ByteArray,
    val fileName: String,
    val contentType: String,
)
