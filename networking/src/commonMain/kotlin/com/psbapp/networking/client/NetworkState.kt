package com.psbapp.networking.client

import com.psbapp.networking.utils.BaseProgressBarState

sealed class NetworkState<out T> {

    data class Loading<T>(val progressBarState: BaseProgressBarState) :
        NetworkState<T>()

    data class Success<T>(val response: T) : NetworkState<T>()

    data class Error<T>(val message: String) : NetworkState<T>()

    companion object {

        fun <T> loading(progressBarState: BaseProgressBarState = BaseProgressBarState.Idle) =
                Loading<T>(progressBarState)


        fun <T> success(data: T) =
                Success(data)

        fun <T> error(message: String) =
                Error<T>(message)

    }
}

inline fun <T, R> NetworkState<T>.mapResponse(transform: (value: T) -> R): NetworkState<R> =
        when (this) {
            is NetworkState.Success -> NetworkState.Success(transform(response))
            is NetworkState.Error -> NetworkState.Error(message)
            is NetworkState.Loading -> NetworkState.loading(BaseProgressBarState.Idle)
        }