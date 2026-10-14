package com.psbapp.utils.share.dialer

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

/**
 * BUG IN CLIENT OF UIKIT: The caller of UIApplication.openURL(_:) needs to migrate to the non-deprecated UIApplication.open(_:options:completionHandler:). Force returning false (NO).
 */
fun phoneDialer(number: String) {
    val telUrl = NSURL.URLWithString(
        "tel:${
            number.replace(
                "[^0-9]".toRegex(),
                ""
            )
        }"
    )

    if (telUrl != null) {
        UIApplication.sharedApplication.openURL(
            telUrl,
            mapOf<Any?, Any>(),
            null
        )
    }
}
