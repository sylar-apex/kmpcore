package com.psbapp.utils.logger

import android.util.Log

internal actual fun platformLog(priority: LogPriority, tag: String, message: String, throwable: Throwable?) {
    val cleanTag = tag.trim()
    when (priority) {
        LogPriority.VERBOSE -> Log.v(cleanTag, message, throwable)
        LogPriority.DEBUG -> Log.d(cleanTag, message, throwable)
        LogPriority.INFO -> Log.i(cleanTag, message, throwable)
        LogPriority.NETWORK -> Log.d(cleanTag, "[NETWORK] $message", throwable)
        LogPriority.WARN -> Log.w(cleanTag, message, throwable)
        LogPriority.ERROR -> Log.e(cleanTag, message, throwable)
    }
}
