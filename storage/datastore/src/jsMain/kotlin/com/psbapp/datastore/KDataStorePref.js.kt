package com.psbapp.datastore


import kotlinx.browser.window

actual class KDataStorePref actual constructor(val dataStoreName: String) {

    private val storage get() = window.localStorage

    /** ------------------ String ------------------ */

    actual suspend fun getValue(key: String): String? {
        return storage.getItem(key)
    }

    actual suspend fun setValue(key: String, value: String) {
        storage.setItem(
            key,
            value
        )
    }

    actual suspend fun getString(key: String, defaultValue: String): String {
        return storage.getItem(key) ?: defaultValue
    }

    actual suspend fun setString(key: String, value: String?) {
        if (value == null) storage.removeItem(key)
        else storage.setItem(
            key,
            value
        )
    }

    actual suspend fun removeString(key: String) {
        storage.removeItem(key)
    }

    /** ------------------ Boolean ------------------ */

    actual suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return storage.getItem(key)?.toBooleanStrictOrNull() ?: defaultValue
    }

    actual suspend fun getBooleanNullable(key: String, defaultValue: Boolean?): Boolean? {
        return storage.getItem(key)?.toBooleanStrictOrNull() ?: defaultValue
    }

    actual suspend fun setBoolean(key: String, value: Boolean?) {
        if (value == null) storage.removeItem(key)
        else storage.setItem(
            key,
            value.toString()
        )
    }

    /** ------------------ Int ------------------ */

    actual suspend fun getInt(key: String, defaultValue: Int): Int {
        return storage.getItem(key)?.toIntOrNull() ?: defaultValue
    }

    actual suspend fun getIntNullable(key: String, defaultValue: Int?): Int? {
        return storage.getItem(key)?.toIntOrNull() ?: defaultValue
    }

    actual suspend fun setInt(key: String, value: Int?) {
        if (value == null) storage.removeItem(key)
        else storage.setItem(
            key,
            value.toString()
        )
    }

    /** ------------------ Long ------------------ */

    actual suspend fun getLong(key: String, defaultValue: Long): Long {
        return storage.getItem(key)?.toLongOrNull() ?: defaultValue
    }

    actual suspend fun getLongNullable(key: String, defaultValue: Long?): Long? {
        return storage.getItem(key)?.toLongOrNull() ?: defaultValue
    }

    actual suspend fun setLong(key: String, value: Long?) {
        if (value == null) storage.removeItem(key)
        else storage.setItem(
            key,
            value.toString()
        )
    }

    /** ------------------ Float ------------------ */

    actual suspend fun getFloat(key: String, defaultValue: Float): Float {
        return storage.getItem(key)?.toFloatOrNull() ?: defaultValue
    }

    actual suspend fun getFloatNullable(key: String, defaultValue: Float?): Float? {
        return storage.getItem(key)?.toFloatOrNull() ?: defaultValue
    }

    actual suspend fun setFloat(key: String, value: Float?) {
        if (value == null) storage.removeItem(key)
        else storage.setItem(
            key,
            value.toString()
        )
    }

    /** ------------------ Double ------------------ */

    actual suspend fun getDouble(key: String, defaultValue: Double): Double {
        return storage.getItem(key)?.toDoubleOrNull() ?: defaultValue
    }

    actual suspend fun getDoubleNullable(key: String, defaultValue: Double?): Double? {
        return storage.getItem(key)?.toDoubleOrNull() ?: defaultValue
    }

    actual suspend fun setDouble(key: String, value: Double?) {
        if (value == null) storage.removeItem(key)
        else storage.setItem(
            key,
            value.toString()
        )
    }

    /** ------------------ Remove & Clear ------------------ */

    actual suspend fun remove(key: String) {
        storage.removeItem(key)
    }

    actual suspend fun clear() {
        storage.clear()
    }
}
