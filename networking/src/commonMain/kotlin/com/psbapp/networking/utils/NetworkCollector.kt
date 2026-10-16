package com.psbapp.networking.utils

import com.psbapp.networking.client.NetworkState
import com.psbapp.utils.capitalizeFirstChar
import com.psbapp.utils.logger.Print
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

inline fun <T> Flow<NetworkState<T>>.onSuccess(crossinline action: (T) -> Unit): Flow<NetworkState<T>> {
    return this.onEach { networkState ->
        if (networkState is NetworkState.Success) {
            action(networkState.response)
        }
    }
}

inline fun <T> Flow<NetworkState<T>>.onError(crossinline action: (String) -> Unit): Flow<NetworkState<T>> {
    return this.onEach { networkState ->
        if (networkState is NetworkState.Error) {
            val message = networkState.message.capitalizeFirstChar()
            Print.logError("Api onError message => $message")
            action(message)
        }
    }
}

inline fun <T> Flow<NetworkState<T>>.onLoading(crossinline action: (isLoading: Boolean) -> Unit): Flow<NetworkState<T>> {
    return this.onEach { networkState ->
        if (networkState is NetworkState.Loading) {
            if (networkState.progressBarState == BaseProgressBarState.Idle)
                action(false)
            else action(true)
        }
    }
}


/// Sync

suspend inline fun <T> Flow<NetworkState<T>>.onSuccessSync(crossinline action: (T) -> Unit) {
    this.collect { networkState ->
        if (networkState is NetworkState.Success) {
            action(networkState.response)
        }
    }
}

suspend inline fun <T> Flow<NetworkState<T>>.onFailureSync(crossinline action: (String) -> Unit) {
    this.collect { networkState ->
        if (networkState is NetworkState.Error) {
            action(networkState.message.capitalizeFirstChar())
        }
    }
}

suspend inline fun <T> Flow<NetworkState<T>>.onLoadingSync(crossinline action: (isLoading: Boolean) -> Unit) {
    this.collect { networkState ->
        if (networkState is NetworkState.Loading) {
            if (networkState.progressBarState == BaseProgressBarState.Idle)
                action(false)
            else action(true)
        }
    }
}
