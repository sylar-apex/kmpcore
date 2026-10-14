package com.psbapp.utils.share

import androidx.compose.runtime.Composable

interface SharingManager {
    @Composable
    fun openBrowser(url: String)

    @Composable
    fun whatsAppShare(message: String)

    fun openOtherApp(packageName: String)

    fun openPhoneDialer(number: String)
}