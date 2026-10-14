package com.psbapp.utils.share.dialer

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.phoneDialer(number: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$number")
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}