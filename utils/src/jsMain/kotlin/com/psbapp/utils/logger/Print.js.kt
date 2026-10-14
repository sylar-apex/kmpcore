package com.psbapp.utils.logger

internal actual fun platformLog(priority: LogPriority, tag: String, message: String, throwable: Throwable?) {
    val label = when (priority) {
        LogPriority.VERBOSE -> "VERBOSE"
        LogPriority.DEBUG -> "DEBUG"
        LogPriority.INFO -> "INFO"
        LogPriority.NETWORK -> "NETWORK"
        LogPriority.WARN -> "WARN"
        LogPriority.ERROR -> "ERROR"
    }
    println("[$label] [$tag] $message")
    throwable?.let { println(it.stackTraceToString()) }
}
