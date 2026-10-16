package com.psbapp.networking.utils

sealed class BaseProgressBarState {

    data object ButtonLoading : BaseProgressBarState()

    data object ScreenLoading : BaseProgressBarState()

    data object FullScreenLoading : BaseProgressBarState()

    data object ShimmerLoading : BaseProgressBarState()

    data object LoadingWithLogo : BaseProgressBarState()

    data object Idle : BaseProgressBarState()

}