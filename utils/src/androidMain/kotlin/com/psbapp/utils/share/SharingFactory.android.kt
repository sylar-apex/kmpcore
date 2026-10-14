package com.psbapp.utils.share

import android.content.Context
import androidx.compose.runtime.Composable
import com.psbapp.utils.share.browser.openWebBrowser
import com.psbapp.utils.share.dialer.phoneDialer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual object SharingFactory : KoinComponent {
    private val context: Context by inject<Context>()

    @Composable
    actual fun openBrowser(url: String, onPageOpened: () -> Unit) {
        openWebBrowser(
            url,
            onPageOpened
        )
    }

    @Composable
    actual fun whatsAppShare(message: String) {
    }

    actual fun openOtherApp(packageName: String) {
    }

    actual fun openPhoneDialer(number: String) {
        context.phoneDialer(number)
    }

}