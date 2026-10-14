package com.psbapp.utils.clipboard

import androidx.compose.ui.platform.ClipEntry
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor



actual suspend fun ClipEntry.readText(): String? {
    // efficient implementation on desktop using AWT directly
    return try {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            val data = clipboard.getData(DataFlavor.stringFlavor)
            data as? String
        } else {
            null
        }
    } catch (e: Throwable) {
        null
    }
}
