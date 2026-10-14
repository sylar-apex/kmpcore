package com.psbapp.utils.share

expect object ReceiptShareHelper {
    fun shareAsImage(imageBytes: ByteArray, fileName: String = "receipt.png")
    fun openAsPdf(imageBytes: ByteArray, fileName: String = "receipt.pdf")
}
