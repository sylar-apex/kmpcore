package com.psbapp.utils.share

import androidx.compose.runtime.Composable


actual object SharingFactory {

    @Composable
    actual fun openBrowser(url: String, onPageOpened: () -> Unit) {
//        openWebBrowser(url, onPageOpened)
    }

    @Composable
    actual fun whatsAppShare(message: String) {
    }

    actual fun openOtherApp(packageName: String) {
    }

    actual fun openPhoneDialer(number: String) {
//        phoneDialer(number)
    }

}