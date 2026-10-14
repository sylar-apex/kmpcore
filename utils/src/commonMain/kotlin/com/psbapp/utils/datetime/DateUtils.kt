package com.psbapp.utils.datetime

/**
 * Obtains the current [LocalDate] from the specified [clock] and [timeZone].
 *
 * Using this method allows the use of an alternate clock or timezone for testing.
 */
// public fun LocalDate.Companion.now(
//    clock: Clock = Clock.System,
//    timeZone: TimeZone = TimeZone.currentSystemDefault(),
// ): LocalDate = clock.todayIn(timeZone)
import com.psbapp.utils.capitalizeFirstChar
import com.psbapp.utils.capitalizeWords
import com.psbapp.utils.domain.string.StringFormat
import com.psbapp.utils.getSubString
import com.psbapp.utils.logger.Print
import kotlinx.datetime.DateTimeArithmeticException
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

@OptIn(FormatStringsInDatetimeFormats::class, kotlin.time.ExperimentalTime::class)
fun getCurrentDate(pattern: String = DateTimeFormat.yyyy_MM_dd.value): String {
        val currentDateTime =
                Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())

        // 1. Format to a stable intermediate string (numeric only) using kotlinx-datetime
        // using "yyyy-MM-dd HH:mm:ss" as it contains all components and is numeric
        val intermediatePattern = DateTimeFormat.yyyy_MM_dd_HH_mm_ss.value
        val intermediateFormatter =
                kotlinx.datetime.LocalDateTime.Format { byUnicodePattern(intermediatePattern) }
        val intermediateDateString = intermediateFormatter.format(currentDateTime)

        // 2. Use the platform-specific formatDateTime which handles Locale and text patterns better
        return dateTimeFormatter(
                dateTime = intermediateDateString,
                currentFormat = intermediatePattern,
                outFormat = pattern
        )
}

/**
 * Returns a [LocalDate] that results from adding the [value] number of days to this date.
 *
 * If the [value] is positive, the returned date is later than this date. If the [value] is
 * negative, the returned date is earlier than this date.
 *
 * @throws DateTimeArithmeticException if the result exceeds the boundaries of [LocalDate].
 */
public fun LocalDate.plusDays(value: Int): LocalDate = plus(value, DateTimeUnit.DAY)

/**
 * Returns a [LocalDate] that results from subtracting the [value] number of days from this date.
 *
 * If the [value] is positive, the returned date is later than this date. If the [value] is
 * negative, the returned date is earlier than this date.
 *
 * @throws DateTimeArithmeticException if the result exceeds the boundaries of [LocalDate].
 */
public fun LocalDate.minusDays(value: Int): LocalDate = minus(value, DateTimeUnit.DAY)

/**
 * Returns a [LocalDate] that results from adding the [value] number of months to this date.
 *
 * If the [value] is positive, the returned date is later than this date. If the [value] is
 * negative, the returned date is earlier than this date.
 *
 * @throws DateTimeArithmeticException if the result exceeds the boundaries of [LocalDate].
 */
public fun LocalDate.plusMonths(value: Int): LocalDate = plus(value, DateTimeUnit.MONTH)

/**
 * Returns a [LocalDate] that results from subtracting the [value] number of months from this date.
 *
 * If the [value] is positive, the returned date is later than this date. If the [value] is
 * negative, the returned date is earlier than this date.
 *
 * @throws DateTimeArithmeticException if the result exceeds the boundaries of [LocalDate].
 */
public fun LocalDate.minusMonths(value: Int): LocalDate = minus(value, DateTimeUnit.MONTH)

/**
 * Returns a [LocalDate] that results from adding the [value] number of years to this date.
 *
 * If the [value] is positive, the returned date is later than this date. If the [value] is
 * negative, the returned date is earlier than this date.
 *
 * @throws DateTimeArithmeticException if the result exceeds the boundaries of [LocalDate].
 */
public fun LocalDate.plusYears(value: Int): LocalDate = plus(value, DateTimeUnit.YEAR)

/**
 * Returns a [LocalDate] that results from subtracting the [value] number of years from this date.
 *
 * If the [value] is positive, the returned date is later than this date. If the [value] is
 * negative, the returned date is earlier than this date.
 *
 * @throws DateTimeArithmeticException if the result exceeds the boundaries of [LocalDate].
 */
public fun LocalDate.minusYears(value: Int): LocalDate = minus(value, DateTimeUnit.YEAR)

internal fun LocalDate.plusWeeks(value: Int): LocalDate = plus(value, DateTimeUnit.WEEK)

internal fun LocalDate.minusWeeks(value: Int): LocalDate = minus(value, DateTimeUnit.WEEK)

// internal fun LocalDate.weeksUntil(other: LocalDate): Int = until(other, DateTimeUnit.WEEK)

// E.g DayOfWeek.SATURDAY.daysUntil(DayOfWeek.TUESDAY) = 3
internal fun DayOfWeek.daysUntil(other: DayOfWeek) = (7 + (other.ordinal - ordinal)) % 7

// E.g DayOfWeek.SATURDAY.plusDays(3) = DayOfWeek.TUESDAY
internal fun DayOfWeek.plusDays(days: Int): DayOfWeek {
        val amount = (days % 7)
        return DayOfWeek.entries[(ordinal + (amount + 7)) % 7]
}

fun String.getMontShortName(
        maxLength: Int = 3,
        stringFormat: StringFormat = StringFormat.CAPITALIZE
): String {
        return when (stringFormat) {
                StringFormat.LOWER_CASE -> this.getSubString(maxLength).lowercase()
                StringFormat.UPPER_CASE -> this.getSubString(maxLength).uppercase()
                StringFormat.CAPITALIZE ->
                        this.getSubString(maxLength).lowercase().capitalizeFirstChar()
                StringFormat.CAPITALIZE_WORD ->
                        this.getSubString(maxLength).lowercase().capitalizeWords()
        }
}

@OptIn(kotlin.time.ExperimentalTime::class)
fun getCurrentUtcTime(format: String): String {
        val now = kotlin.time.Clock.System.now()
        val dateTime = now.toLocalDateTime(kotlinx.datetime.TimeZone.UTC)

        fun Int.pad(): String = this.toString().padStart(2, '0')

        // Construct intermediate string with UTC values
        val intermediateFormat = DateTimeFormat.yyyy_MM_dd_HH_mm_ss.value
        val intermediateString =
                "${dateTime.year}-${dateTime.month.number.pad()}-${dateTime.day.pad()} ${dateTime.hour.pad()}:${dateTime.minute.pad()}:${dateTime.second.pad()}"

        return dateTimeFormatter(
                dateTime = intermediateString,
                currentFormat = intermediateFormat,
                outFormat = format
        )
}

@OptIn(kotlin.time.ExperimentalTime::class)
fun getCurrentTime(): String {
    val currentDateTime =
            Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
    return "${currentDateTime.hour.toString().padStart(2, '0')}:${currentDateTime.minute.toString().padStart(2, '0')}"
}

@OptIn(kotlin.time.ExperimentalTime::class)
fun getCurrentDayName(): String {
    val currentDateTime =
            Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault())
    return currentDateTime.dayOfWeek.name.lowercase().capitalizeFirstChar()
}


fun String.formatDateTime(currentFormat: String, outFormat: String): String {
    return dateTimeFormatter(this, currentFormat, outFormat)
}
fun String.formatDateTime(outFormat: String): String {
    val currentFormat = findDateTimeFormatFinder(this)
    Print.log("Print => currentFormat $currentFormat")
    return dateTimeFormatter(this, currentFormat, outFormat)
}

