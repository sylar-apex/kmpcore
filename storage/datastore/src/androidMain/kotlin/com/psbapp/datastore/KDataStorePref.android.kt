package com.psbapp.datastore


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import okio.Path.Companion.toPath
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


actual class KDataStorePref actual constructor(val dataStoreName: String) : KoinComponent {


    private var dataStore: DataStore<Preferences>

    init {
        println("Print KDataStorePref: init")
        val context: Context by inject()
        dataStore = PreferenceDataStoreFactory.createWithPath(
            produceFile = {
                "${context.filesDir.path}/$dataStoreName".toPath()
            }
        )
    }


    /** ------------------ String ------------------ */

    actual suspend fun getValue(key: String): String? {
        return dataStore.data.first()[stringPreferencesKey(key)]
    }

    actual suspend fun setValue(key: String, value: String) {
        dataStore.edit { it[stringPreferencesKey(key)] = value }
    }

    actual suspend fun getString(key: String, defaultValue: String): String {
        return dataStore.data.first()[stringPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun setString(key: String, value: String?) {
        dataStore.edit {
            if (value == null) it.remove(stringPreferencesKey(key))
            else it[stringPreferencesKey(key)] = value
        }
    }

    actual suspend fun removeString(key: String) {
        dataStore.edit { it.remove(stringPreferencesKey(key)) }
    }

    /** ------------------ Boolean ------------------ */

    actual suspend fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return dataStore.data.first()[booleanPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun getBooleanNullable(key: String, defaultValue: Boolean?): Boolean? {
        return dataStore.data.first()[booleanPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun setBoolean(key: String, value: Boolean?) {
        dataStore.edit {
            if (value == null) it.remove(booleanPreferencesKey(key))
            else it[booleanPreferencesKey(key)] = value
        }
    }

    /** ------------------ Int ------------------ */

    actual suspend fun getInt(key: String, defaultValue: Int): Int {
        return dataStore.data.first()[intPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun getIntNullable(key: String, defaultValue: Int?): Int? {
        return dataStore.data.first()[intPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun setInt(key: String, value: Int?) {
        dataStore.edit {
            if (value == null) it.remove(intPreferencesKey(key))
            else it[intPreferencesKey(key)] = value
        }
    }

    /** ------------------ Long ------------------ */

    actual suspend fun getLong(key: String, defaultValue: Long): Long {
        return dataStore.data.first()[longPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun getLongNullable(key: String, defaultValue: Long?): Long? {
        return dataStore.data.first()[longPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun setLong(key: String, value: Long?) {
        dataStore.edit {
            if (value == null) it.remove(longPreferencesKey(key))
            else it[longPreferencesKey(key)] = value
        }
    }

    /** ------------------ Float ------------------ */

    actual suspend fun getFloat(key: String, defaultValue: Float): Float {
        return dataStore.data.first()[floatPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun getFloatNullable(key: String, defaultValue: Float?): Float? {
        return dataStore.data.first()[floatPreferencesKey(key)] ?: defaultValue
    }

    actual suspend fun setFloat(key: String, value: Float?) {
        dataStore.edit {
            if (value == null) it.remove(floatPreferencesKey(key))
            else it[floatPreferencesKey(key)] = value
        }
    }

    /** ------------------ Double (store as String) ------------------ */

    actual suspend fun getDouble(key: String, defaultValue: Double): Double {
        val stored = dataStore.data.first()[stringPreferencesKey(key)]
        return stored?.toDoubleOrNull() ?: defaultValue
    }

    actual suspend fun getDoubleNullable(key: String, defaultValue: Double?): Double? {
        val stored = dataStore.data.first()[stringPreferencesKey(key)]
        return stored?.toDoubleOrNull() ?: defaultValue
    }

    actual suspend fun setDouble(key: String, value: Double?) {
        dataStore.edit {
            if (value == null) it.remove(stringPreferencesKey(key))
            else it[stringPreferencesKey(key)] = value.toString()
        }
    }

    /** ------------------ Remove & Clear ------------------ */

    actual suspend fun remove(key: String) {
        // Generic remove: remove from all possible types
        dataStore.edit {
            it.remove(stringPreferencesKey(key))
            it.remove(booleanPreferencesKey(key))
            it.remove(intPreferencesKey(key))
            it.remove(longPreferencesKey(key))
            it.remove(floatPreferencesKey(key))
        }
    }

    actual suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}
