package com.psbapp.utils.share

import androidx.compose.runtime.Composable

expect object SharingFactory {

    @Composable
    fun openBrowser(url: String, onPageOpened: () -> Unit)

    @Composable
    fun whatsAppShare(message: String)

    fun openOtherApp(packageName: String)

    fun openPhoneDialer(number: String)
}

