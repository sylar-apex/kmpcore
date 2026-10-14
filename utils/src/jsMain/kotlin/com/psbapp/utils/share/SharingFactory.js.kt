package com.psbapp.utils.share

import androidx.compose.runtime.Composable
import kotlinx.browser.window

actual object SharingFactory {

    @Composable
    actual fun openBrowser(url: String, onPageOpened: () -> Unit) {
        window.open(url, "_blank")
        onPageOpened()
    }

    @Composable
    actual fun whatsAppShare(message: String) {
        window.open("https://wa.me/?text=${encodeURIComponent(message)}", "_blank")
    }

    /** The browser sandbox cannot launch another installed app. */
    actual fun openOtherApp(packageName: String) {
    }

    actual fun openPhoneDialer(number: String) {
        window.open("tel:$number", "_self")
    }
}

private external fun encodeURIComponent(value: String): String
