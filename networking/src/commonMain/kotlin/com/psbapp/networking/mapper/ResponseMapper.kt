package com.psbapp.networking.mapper

import com.psbapp.networking.client.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

inline fun <T, R> Flow<NetworkState<T>>.mapApiData(crossinline transform: (T) -> R): Flow<NetworkState<R>> {
    return this.map { networkState ->
        when (networkState) {
            is NetworkState.Loading -> NetworkState.Loading(networkState.progressBarState)
            is NetworkState.Success -> {
                val mappedData = transform(networkState.response)
                NetworkState.Success(mappedData)
            }

            is NetworkState.Error -> NetworkState.Error(networkState.message)
        }
    }
}

suspend inline fun <T, R> Flow<NetworkState<T>>.mapApiDataSync(crossinline transform: (T) -> R): NetworkState<R> {
    return this.first().let { networkState ->
        when (networkState) {
            is NetworkState.Loading -> NetworkState.Loading(networkState.progressBarState)
            is NetworkState.Success -> {
                try {
                    val mappedData = transform(networkState.response)
                    NetworkState.Success(mappedData)
                } catch (e: Exception) {
                    NetworkState.Error(e.message ?: "Unknown error occurred during transformation")
                }
            }

            is NetworkState.Error -> NetworkState.Error(networkState.message)
        }
    }
}
