package com.psbapp.utils

enum class PlatformType {
    IOS,
    Android,
    Desktop,
    Web
}

interface Platform {
    val type: PlatformType
}

expect fun getPlatform(): Platform