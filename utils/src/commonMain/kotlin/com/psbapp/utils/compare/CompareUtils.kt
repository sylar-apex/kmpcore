package com.psbapp.utils.compare

inline fun <reified T> List<T>.equalTo(second: List<T>, isCheckOrder: Boolean = false): Boolean {
    if (this.size != second.size) {
        return false
    }

    return if (isCheckOrder) {
        this == second  // Check exact equality including order
    } else {
        this.toSet() == second.toSet()  // Check equality without order
    }
}


fun String.equalTo(second: String, ignoreCase: Boolean = true): Boolean {
    return this.equals(
        second,
        ignoreCase
    )
}

fun <T> T.equalTo(second: T): Boolean {
    return this == second
}
