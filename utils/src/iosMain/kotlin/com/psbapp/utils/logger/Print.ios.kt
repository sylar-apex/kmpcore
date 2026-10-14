package com.psbapp.utils.logger

import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier
import platform.Foundation.NSLog

/**
 * iOS logging, routed through Napier and out via `NSLog`.
 *
 * The reason for both halves: `println` writes to stdout, and on a physical iPhone an app's stdout
 * goes nowhere — the redirect into the system log only exists while a debugger is attached. So the
 * old implementation was visible in Xcode's console and invisible everywhere else, including any
 * tool reading the device's log. `NSLog` writes to the unified log, which is what the device
 * actually keeps.
 *
 * Napier gives the shared code one logging API across platforms; the sink below is what guarantees
 * where the line ends up, rather than relying on whichever writer Napier's default picks.
 */
private class NSLogAntilog : Antilog() {

    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {
        val label = when (priority) {
            LogLevel.VERBOSE -> "VERBOSE"
            LogLevel.DEBUG -> "DEBUG"
            LogLevel.INFO -> "INFO"
            LogLevel.WARNING -> "WARN"
            LogLevel.ERROR -> "ERROR"
            LogLevel.ASSERT -> "ASSERT"
        }

        val line = buildString {
            append('[').append(label).append(']')
            tag?.takeIf { it.isNotBlank() }?.let { append(" [").append(it.trim()).append(']') }
            message?.let { append(' ').append(it) }
            throwable?.let { append('\n').append(it.stackTraceToString()) }
        }

        // "%s" and the text as an argument, never the text as the format itself: a message
        // containing a percent sign would otherwise be read as a format specifier, which reads
        // arbitrary stack memory and can crash the app that was only trying to log.
        NSLog("%s", line)
    }
}

/**
 * Installed once, on first use.
 *
 * Lazily rather than from an initialiser somewhere in the app, so nothing has to remember to call a
 * setup function before the first log line — and so a shared module stays self-contained.
 */
private val napier: Unit by lazy { Napier.base(NSLogAntilog()) }

internal actual fun platformLog(
    priority: LogPriority,
    tag: String,
    message: String,
    throwable: Throwable?
) {
    napier

    val level = when (priority) {
        LogPriority.VERBOSE -> LogLevel.VERBOSE
        LogPriority.DEBUG -> LogLevel.DEBUG
        LogPriority.INFO -> LogLevel.INFO
        // Napier has no network level; it keeps its own label in the message so the line still says
        // what it is, and the level stays one the log filters understand.
        LogPriority.NETWORK -> LogLevel.DEBUG
        LogPriority.WARN -> LogLevel.WARNING
        LogPriority.ERROR -> LogLevel.ERROR
    }

    val text = if (priority == LogPriority.NETWORK) "[NETWORK] $message" else message
    Napier.log(priority = level, tag = tag.trim(), throwable = throwable, message = text)
}
