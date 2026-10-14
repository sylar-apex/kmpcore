package com.psbapp.utils.clipboard

import androidx.compose.ui.platform.ClipEntry
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlin.js.Promise

// External declarations for Clipboard API
private external interface ClipboardApi : JsAny {
    fun readText(): Promise<JsString>
}

private external interface NavigatorWithClipboard : JsAny {
    val clipboard: ClipboardApi?
}

actual suspend fun ClipEntry.readText(): String? {
    // On Web, we can access the clipboard directly via the Navigator API,
    // ignoring the opaque ClipEntry object if needed.
    return try {
        val navigator = window.navigator.unsafeCast<NavigatorWithClipboard>()
        val clipboard = navigator.clipboard
        if (clipboard != null) {
            val promise = clipboard.readText()
            promise.await<JsString>().toString()
        } else {
            null
        }
    } catch (e: Throwable) {
        println("Clipboard read failed: ${e.message}")
        null
    }
}
