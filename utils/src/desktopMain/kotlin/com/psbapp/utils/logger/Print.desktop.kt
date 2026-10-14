package com.psbapp.utils.logger

private const val RESET = "[0m"
private const val RED = "[31m"
private const val GREEN = "[32m"
private const val YELLOW = "[33m"
private const val BLUE = "[34m"
private const val PURPLE = "[35m"
private const val CYAN = "[36m"
private const val GRAY = "[90m"

internal actual fun platformLog(priority: LogPriority, tag: String, message: String, throwable: Throwable?) {
    val (color, label) = when (priority) {
        LogPriority.VERBOSE -> GRAY to "VERBOSE"
        LogPriority.DEBUG -> BLUE to "DEBUG"
        LogPriority.INFO -> CYAN to "INFO"
        LogPriority.NETWORK -> PURPLE to "NETWORK"
        LogPriority.WARN -> YELLOW to "WARN"
        LogPriority.ERROR -> RED to "ERROR"
    }
    println("$color[$label] [$tag] $message$RESET")
    throwable?.let { println("$color${it.stackTraceToString()}$RESET") }
}
