package com.psbapp.utils.share.browser

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun openWebBrowser(url: String, onPageOpened: () -> Unit) {
    val context = LocalContext.current

    context.apply {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        onPageOpened()
        context.startActivity(intent)
    }

}