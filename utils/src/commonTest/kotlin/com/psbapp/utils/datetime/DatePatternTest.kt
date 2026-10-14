package com.psbapp.utils.datetime

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DatePatternTest {
    @Test fun canonicalRoundTrip() {
        val iso = "2026-07-28 22:45:40"
        val canonical = dateTimeFormatter(iso, "yyyy-MM-dd HH:mm:ss", "dd MMM yyyy hh:mm:ss a")
        assertEquals("28 Jul 2026 10:45:40 PM", canonical)
        assertEquals(iso, dateTimeFormatter(canonical, "dd MMM yyyy hh:mm:ss a", "yyyy-MM-dd HH:mm:ss"))
    }

    @Test fun shortFormats() {
        val canonical = "28 Jul 2026 10:45:40 PM"
        assertEquals("28-07-2026 22:45", dateTimeFormatter(canonical, "dd MMM yyyy hh:mm:ss a", "dd-MM-yyyy HH:mm"))
        assertEquals("07/28/2026 10:45 PM", dateTimeFormatter(canonical, "dd MMM yyyy hh:mm:ss a", "MM/dd/yyyy hh:mm a"))
        assertEquals("Tuesday, July 28, 2026", dateTimeFormatter(canonical, "dd MMM yyyy hh:mm:ss a", "EEEE, MMMM dd, yyyy"))
        assertEquals("2026-07-28T22:45:40", dateTimeFormatter(canonical, "dd MMM yyyy hh:mm:ss a", "yyyy-MM-dd'T'HH:mm:ss"))
    }

    @Test fun midnightAndNoon() {
        assertEquals("12:00 AM", dateTimeFormatter("2026-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss", "hh:mm a"))
        assertEquals("12:30 PM", dateTimeFormatter("2026-01-01 12:30:00", "yyyy-MM-dd HH:mm:ss", "hh:mm a"))
        assertEquals("00:00", dateTimeFormatter("01 Jan 2026 12:00:00 AM", "dd MMM yyyy hh:mm:ss a", "HH:mm"))
    }

    @Test fun rejectsMismatch() {
        assertTrue(dateTimeFormatter("not a date", "yyyy-MM-dd HH:mm:ss", "dd MMM yyyy").isEmpty())
    }
}
