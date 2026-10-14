package com.psbapp.utils.clipboard

import androidx.compose.ui.platform.ClipEntry
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlin.js.Promise

/**
 * On the web the clipboard is read straight from the Navigator API, so the opaque [ClipEntry] is
 * not consulted. Reading is permission gated and rejects when the tab is not focused.
 */
actual suspend fun ClipEntry.readText(): String? {
    return try {
        val clipboard = window.navigator.asDynamic().clipboard ?: return null
        @Suppress("UNCHECKED_CAST")
        (clipboard.readText() as Promise<String>).await()
    } catch (e: Throwable) {
        println("Clipboard read failed: ${e.message}")
        null
    }
}
