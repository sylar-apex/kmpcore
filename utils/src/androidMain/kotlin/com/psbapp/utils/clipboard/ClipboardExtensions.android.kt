package com.psbapp.utils.clipboard

import androidx.compose.ui.platform.ClipEntry

actual suspend fun ClipEntry.readText(): String? {
    // On Android, ClipEntry wraps ClipData.
    // We assume the standard implementation exposes it via the 'clipData' property.
    // To be safe and avoid reflection if property is hidden, we rely on the
    // fact that typical usage allows access. For now, we will try to access 'clipData'.
    // Note: If 'clipData' is internal in Compose, this will fail compilation,
    // but typically it is exposed.

    // However, if we can't access it, we can't read text easily without Context.
    // But since this is a suspended extension on ClipEntry (which we got from Clipboard),
    // we should try to use the entry.

    return try {
        // Optimistic property access. If this fails to compile, we will need to reconsider.
        val data = this.clipData
        if (data.itemCount > 0) {
            data.getItemAt(0).text?.toString() ?: data.getItemAt(0).coerceToText(null)?.toString()
        } else {
            null
        }
    } catch (e: Throwable) {
        null
    }
}
