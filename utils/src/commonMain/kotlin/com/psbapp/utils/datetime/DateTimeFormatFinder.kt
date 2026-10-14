package com.psbapp.utils.datetime

import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * Iterates through all predefined patterns in [DateTimeFormat] and returns the matching format
 * pattern.
 */
@OptIn(FormatStringsInDatetimeFormats::class)
fun findDateTimeFormatFinder(dateTime: String): String {
    if (dateTime.isBlank()) return "Unknown"

    for (format in DateTimeFormat.entries) {
        val pattern = format.value
        try {
            // Try parsing using DateTimeComponents which is more flexible (handles partials and
            // offsets)
            kotlinx.datetime.format.DateTimeComponents.Format { byUnicodePattern(pattern) }
                    .parse(dateTime)
            return pattern
        } catch (e: Exception) {
            // Continue to next format
        }
    }

    return "Unknown"
}
