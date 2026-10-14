package com.psbapp.utils

class JsPlatform : Platform {
    override val type: PlatformType = PlatformType.Web
}

actual fun getPlatform(): Platform {
    return JsPlatform()
}
