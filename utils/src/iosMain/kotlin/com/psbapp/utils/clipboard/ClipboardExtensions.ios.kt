package com.psbapp.utils.clipboard

import androidx.compose.ui.platform.ClipEntry
import platform.UIKit.UIPasteboard

actual suspend fun ClipEntry.readText(): String? {
    // Efficient implementation on iOS using UIPasteboard directly
    return UIPasteboard.generalPasteboard.string
}
