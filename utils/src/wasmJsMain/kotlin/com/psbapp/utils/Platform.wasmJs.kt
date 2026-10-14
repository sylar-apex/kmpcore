package com.psbapp.utils


class WebPlatform : Platform {
    override val type: PlatformType = PlatformType.Web
//    override val type: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform {
    return WebPlatform()
}