package com.psbapp.utils

class IOSPlatform : Platform {
    //    override val name: PlatformType = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val type: PlatformType = PlatformType.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()