package com.psbapp.utils.validation

/** Format checks a feature's `domain/validation` rules can call without a Compose dependency. */
// No identity escapes (`\_`, `\%`, `\@`): Kotlin/JS compiles Regex with the unicode flag, which
// rejects them and throws at first use — the JVM silently allowed them.
private val EMAIL_REGEX = Regex(
    "[a-zA-Z0-9+._%-]{1,256}" +
        "@" +
        "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
        "(" +
        "\\." +
        "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
        ")+"
)

/** Indian mobile number: exactly [MOBILE_LENGTH] digits, first digit 6–9. */
private val MOBILE_REGEX = Regex("^[6-9]\\d{9}$")

/** Indian postal code: exactly [PINCODE_LENGTH] digits, not starting with zero. */
private val PINCODE_REGEX = Regex("^[1-9]\\d{5}$")

/** Website: optional scheme, a host with at least one dot, optional path. */
private val URL_REGEX = Regex("^(https?://)?([\\w-]+\\.)+[a-zA-Z]{2,}(/\\S*)?$")

/** GSTIN: 2-digit state, 10-char PAN, entity digit, 'Z', check char. */
private val GSTIN_REGEX = Regex("^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][1-9A-Z]Z[0-9A-Z]$")

const val MOBILE_LENGTH = 10
const val PINCODE_LENGTH = 6

fun String.isValidEmail(): Boolean = matches(EMAIL_REGEX)

fun String.isValidMobile(): Boolean = matches(MOBILE_REGEX)

fun String.isValidPincode(): Boolean = matches(PINCODE_REGEX)

fun String.isValidUrl(): Boolean = matches(URL_REGEX)

fun String.isValidGstin(): Boolean = matches(GSTIN_REGEX)
