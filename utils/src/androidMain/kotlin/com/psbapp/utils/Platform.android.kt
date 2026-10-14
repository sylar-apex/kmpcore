package com.psbapp.utils

class AndroidPlatform : Platform {
    override val type: PlatformType = PlatformType.Android
//    override val type: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()