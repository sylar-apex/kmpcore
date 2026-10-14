package com.psbapp.utils.datetime

/**
 * Reformats [dateTime] from [currentFormat] into [outFormat], or returns an empty string when the
 * input does not match the format.
 *
 * Shared across platforms via [DatePattern] — the previous per-platform implementations accepted
 * different pattern letters, so a pattern that worked on desktop echoed its input back on web.
 */
fun dateTimeFormatter(dateTime: String, currentFormat: String, outFormat: String): String {
    val parsed = DatePattern.parse(dateTime, currentFormat) ?: return ""
    return DatePattern.format(parsed, outFormat)
}
