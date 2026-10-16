package com.psbapp.networking.cache

import com.psbapp.datastore.cache.KCache
import com.psbapp.networking.client.NetworkState
import com.psbapp.networking.response.BaseResponse
import com.psbapp.utils.json.KsonUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

/**
 * Typed access to [KCache] for repositories, which cache api responses rather than raw strings.
 * `useCache` and `KCache.isEnabled` behave exactly as they do on the string api.
 */
suspend inline fun <reified T> KCache.getObject(
    key: String,
    useCache: Boolean = true,
    ttlMillis: Long = KCache.TTL_ONE_DAY,
): T? {
    val json = get(key, useCache = useCache, ttlMillis = ttlMillis) ?: return null
    val value = KsonUtil.fromJson<T>(json)
    // Unreadable json means the shape changed under the slot; drop it so the next call refetches.
    if (value == null) clear(key)
    return value
}

suspend inline fun <reified T> KCache.setObject(key: String, value: T, useCache: Boolean = true) {
    set(key, KsonUtil.toJson(value), useCache = useCache)
}

/**
 * Read side of [setCache]: the api envelope parked in [key], or null when the slot is empty,
 * expired, disabled via [useCache] / [KCache.isEnabled], or no longer parses.
 *
 * ```kotlin
 * cache.getCache<Dto>(AppCacheKey.SLOT)?.let { cached ->
 *     return flowOf(NetworkState.Success(mapper.dtoToDomain(cached)))
 * }
 * ```
 */
suspend inline fun <reified T> KCache.getCache(
    key: String,
    useCache: Boolean = true,
    ttlMillis: Long = KCache.TTL_ONE_DAY,
): BaseResponse<T>? = getObject<BaseResponse<T>>(key, useCache = useCache, ttlMillis = ttlMillis)

/**
 * Parks every successful response of an api flow in [key]. [shouldCache] decides per response —
 * use it when only some answers are worth keeping (e.g. only once a record exists server side).
 *
 * ```kotlin
 * return apiService.getData()
 *     .setCache(cache, AppCacheKey.SLOT)
 *     .mapApiData { response -> mapper.dtoToDomain(response) }
 * ```
 */
inline fun <reified T> Flow<NetworkState<BaseResponse<T>>>.setCache(
    cache: KCache,
    key: String,
    useCache: Boolean = true,
    crossinline shouldCache: (BaseResponse<T>) -> Boolean = { true },
): Flow<NetworkState<BaseResponse<T>>> = onEach { state ->
    if (state is NetworkState.Success && shouldCache(state.response)) {
        cache.setObject(key, state.response, useCache = useCache)
    }
}
