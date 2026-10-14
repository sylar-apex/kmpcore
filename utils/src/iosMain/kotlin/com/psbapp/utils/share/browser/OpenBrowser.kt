package com.psbapp.utils.share.browser

import com.psbapp.utils.logger.Print
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal fun openWebBrowser(url: String, onPageOpened: () -> Unit): Boolean {
    val website = NSURL(string = url)
    return if (UIApplication.sharedApplication().canOpenURL(website)) {
        runCatching {
            var completed = false
            UIApplication.sharedApplication()
                .openURL(
                    website,
                    mapOf<Any?, Any>()
                ) { success ->
                    onPageOpened()
                    completed = success
                }
            completed
        }.getOrElse {
            Print.log(" Failed to open the URL. ${it.message}")
            false
        }
    } else {
        Print.log(" Couldn't open the URL.")
        false
    }
}
