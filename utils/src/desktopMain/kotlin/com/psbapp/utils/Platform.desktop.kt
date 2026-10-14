package com.psbapp.utils


class DesktopPlatform : Platform {
    override val type: PlatformType = PlatformType.Desktop
//    override val type: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform {
    return DesktopPlatform()
}