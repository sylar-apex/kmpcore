package com.psbapp.datastore.cache

import com.psbapp.datastore.KDataStorePref
import com.psbapp.datastore.cache.KCache.Companion.isEnabled
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Key–value cache on top of [KDataStorePref]: a payload parked under a key with the write time
 * next to it. Callers read the key first and only hit their source on a miss.
 *
 * Every read and write takes a `useCache` switch, so a caller can bypass the cache for one call
 * without touching the stored value, and [isEnabled] turns the whole thing off for the process —
 * both exist so a debug build or a test can always work against live data.
 *
 * ```kotlin
 * cache.set(KEY, json)                      // store
 * cache.get(KEY)                            // cached value, or null when missing/stale
 * cache.get(KEY, useCache = false)          // always null → the caller refetches
 * cache.set(KEY, json, useCache = false)    // nothing is written
 * KCache.isEnabled = false                  // whole app runs uncached
 * ```
 */
class KCache(private val pref: KDataStorePref) {

    /**
     * Cached value, or null when the key is empty, older than [ttlMillis], caching is off for the
     * process, or [useCache] is false.
     */
    @OptIn(ExperimentalTime::class)
    suspend fun get(
        key: String,
        useCache: Boolean = true,
        ttlMillis: Long = TTL_ONE_DAY,
    ): String? {
        if (!useCache || !isEnabled) return null

        val value = pref.getValue(valueKey(key))
        if (value.isNullOrBlank()) return null

        val savedAt = pref.getLong(timeKey(key))
        val age = Clock.System.now().toEpochMilliseconds() - savedAt
        // A clock moved backwards leaves a negative age — treat that as stale too.
        if (savedAt <= 0L || age !in 0 until ttlMillis) {
            clear(key)
            return null
        }
        return value
    }

    /** Stores [value] under [key]. A blank value, `useCache = false` or [isEnabled] off writes nothing. */
    @OptIn(ExperimentalTime::class)
    suspend fun set(key: String, value: String, useCache: Boolean = true) {
        if (!useCache || !isEnabled || value.isBlank()) return

        pref.setValue(valueKey(key), value)
        pref.setLong(timeKey(key), Clock.System.now().toEpochMilliseconds())
    }

    /** Drops one key. Everything dies together with [KDataStorePref.clear] at logout. */
    suspend fun clear(key: String) {
        pref.removeString(valueKey(key))
        pref.setLong(timeKey(key), null)
    }

    /** True while [key] holds a value that is still within [ttlMillis]. */
    suspend fun has(key: String, ttlMillis: Long = TTL_ONE_DAY): Boolean =
        get(key, ttlMillis = ttlMillis) != null

    private fun valueKey(key: String) = "$VALUE_PREFIX$key"

    private fun timeKey(key: String) = "$TIME_PREFIX$key"

    companion object {
        /** Default lifetime of a cached payload. */
        const val TTL_ONE_DAY = 24L * 60 * 60 * 1000

        /** For state that cannot go stale on its own and only dies with the datastore. */
        const val TTL_FOREVER = Long.MAX_VALUE

        private const val VALUE_PREFIX = "CACHE_"
        private const val TIME_PREFIX = "CACHE_AT_"

        /**
         * Process wide switch. Set it to false — from a debug menu, a test setup or an app's
         * startup — and every read misses and every write is dropped, without clearing what is
         * already stored.
         */
        var isEnabled: Boolean = true
    }
}
