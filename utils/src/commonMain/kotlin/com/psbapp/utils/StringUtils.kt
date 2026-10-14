package com.psbapp.utils

import kotlin.math.pow
import kotlin.math.round

val String.Companion.COMMA: String by lazy { "," }
val Int.Companion.EMPTY: Int by lazy { 0 }
val Long.Companion.EMPTY: Long by lazy { 0L }
val Float.Companion.EMPTY: Float by lazy { 0f }
val Double.Companion.EMPTY: Double by lazy { 0.0 }
val Boolean.Companion.EMPTY: Boolean by lazy { false }
val String.Companion.EMPTY: String by lazy { "" }

/** This Extension field of type string returns the value of string or empty */
val Int?.value: Int get() = this ?: Int.EMPTY
val Long?.value: Long get() = this ?: Long.EMPTY
val Float?.value: Float get() = this ?: Float.EMPTY
val Double?.value: Double get() = this ?: Double.EMPTY
val Boolean?.value: Boolean get() = this ?: Boolean.EMPTY
val String?.value: String get() = this ?: String.EMPTY
val String?.valueNa: String get() = this ?: "N/A"

//10.5678.round(2)    // 10.57
//10.5678f.round(1)   // 10.6
fun Double.round(decimals: Int = 2): Double {
    val factor = 10.0.pow(decimals)
    return round(this * factor) / factor
}

fun Float.round(decimals: Int = 2): Float {
    val factor = 10f.pow(decimals)
    return (round(this * factor) / factor)
}

fun Double.clean(): String =
        if (this % 1.0 == 0.0) this.toInt().toString() else this.toString()

fun Float.clean(): String =
        if (this % 1f == 0f) this.toInt().toString() else this.toString()

fun String.cleanNumber(): String {
    return if (this.matches(Regex("\\d+\\.0+"))) {
        this.substringBefore(".")
    } else this
}


// This method is added for comparing contents of strings with ignoring case
fun String?.containsIgnoreCase(other: String?): Boolean {
    if (this == null || other == null) return false
    return this.contains(
        other,
        ignoreCase = true
    )
}

/**
 * Essential for Phone no.
 */
fun String.isDigitsOnly(): Boolean = this.all { it.isDigit() }


/**
 * To capitalize first char in a string.
 */
fun String?.capitalizeFirstChar(): String {
    val s = this
    if (s.isNullOrEmpty()) {
        return String.EMPTY
    }
    val first = s[0]
    return if (first.isUpperCase()) {
        s
    } else {
        first.uppercaseChar().toString() + s.substring(1)
    }
}

fun String.getFirstName() = this.substringBefore(" ")
fun String.getLastName() = this.substringAfter(
    " ",
    ""
).trim()

fun concatenateStrings(first: String, second: String, third: String): String {
    return StringBuilder().apply {
        append("$first-")
        append("$second-")
        append(third)
    }.toString()
}


fun String.capitalizeWords(): String {
    return this
        .split(" ")
        .joinToString(" ") { word ->
            word.lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}

fun String.isValidName(): Boolean {
    val namePattern = "^[a-z A-Z]{0,50}$"
    return this.matches(Regex(namePattern))
}

fun String.addPrefixZero(): String = this.padStart(
    2,
    '0'
)


fun String.getSubString(maxLength: Int): String {
    return if (this.length > maxLength) this.substring(
        0,
        maxLength
    ) else this
}


fun String.splitStringIntoChunks(chunkSize: Int = 20): List<String> {
    return this.chunked(chunkSize)
}

fun String.equalIgnore(str: String): Boolean {
    return this.equals(
        str,
        true
    )
}
