package com.psbapp.networking.upload

object UploadSpec {
    /** Multipart field name most upload routes read the file from. */
    const val DEFAULT_FIELD = "file"

    /** Fallback message when the server or the transport gives none. */
    const val FAILED_MESSAGE = "Upload failed"
}
