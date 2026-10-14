package com.psbapp.utils.share

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import java.io.ByteArrayOutputStream

actual fun ImageBitmap.toPngByteArray(): ByteArray {
    val hwBitmap = this.asAndroidBitmap()
    val original = hwBitmap.copy(Bitmap.Config.ARGB_8888, false)
    val whiteBgBitmap = Bitmap.createBitmap(original.width, original.height, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(whiteBgBitmap)
    canvas.drawColor(android.graphics.Color.WHITE)
    canvas.drawBitmap(original, 0f, 0f, null)
    original.recycle()
    val stream = ByteArrayOutputStream()
    whiteBgBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    whiteBgBitmap.recycle()
    return stream.toByteArray()
}
