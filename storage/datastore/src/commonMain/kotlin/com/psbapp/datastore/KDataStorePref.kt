package com.psbapp.datastore

expect class KDataStorePref(dataStoreName: String) {
    // String methods
    suspend fun getValue(key: String): String?
    suspend fun setValue(key: String, value: String)
    suspend fun getString(key: String, defaultValue: String = ""): String
    suspend fun setString(key: String, value: String?)
    suspend fun removeString(key: String)

    // Boolean methods
    suspend fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    suspend fun getBooleanNullable(key: String, defaultValue: Boolean? = null): Boolean?
    suspend fun setBoolean(key: String, value: Boolean?)

    // Int methods
    suspend fun getInt(key: String, defaultValue: Int = 0): Int
    suspend fun getIntNullable(key: String, defaultValue: Int? = null): Int?
    suspend fun setInt(key: String, value: Int?)

    // Long methods
    suspend fun getLong(key: String, defaultValue: Long = 0L): Long
    suspend fun getLongNullable(key: String, defaultValue: Long? = null): Long?
    suspend fun setLong(key: String, value: Long?)

    // Float methods
    suspend fun getFloat(key: String, defaultValue: Float = 0F): Float
    suspend fun getFloatNullable(key: String, defaultValue: Float? = null): Float?
    suspend fun setFloat(key: String, value: Float?)

    // Double methods
    suspend fun getDouble(key: String, defaultValue: Double = 0.0): Double
    suspend fun getDoubleNullable(key: String, defaultValue: Double? = null): Double?
    suspend fun setDouble(key: String, value: Double?)

    // Clear methods
    suspend fun clear()
    suspend fun remove(key: String)
}