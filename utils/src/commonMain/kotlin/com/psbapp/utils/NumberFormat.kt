package com.psbapp.utils

fun formatAmount(amount: String): String {
    val value = amount.toDoubleOrNull() ?: 0.0
    val isNegative = value < 0
    val cents = kotlin.math.round(kotlin.math.abs(value) * 100).toLong()
    val intPart = (cents / 100).toString()
    val fracPart = (cents % 100).toString().padStart(2, '0')
    val grouped = if (intPart.length <= 3) {
        intPart
    } else {
        val lastThree = intPart.takeLast(3)
        val remainder = intPart.dropLast(3)
        remainder.reversed().chunked(2).joinToString(",").reversed() + "," + lastThree
    }
    return "${if (isNegative) "-" else ""}$grouped.$fracPart /-"
}
