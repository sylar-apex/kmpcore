package com.psbapp.utils.appinfo

expect object AppInfoUtils {
    fun getAppVersionName(): String
    fun getAppVersionCode(): String
    fun getDeviceManufacturer(): String
    fun getDeviceModel(): String
    fun getOSVersion(): String
    fun getPackageName(): String
    fun getNetwork(): String
    fun getNetworkCarrier(): String
    fun getDeviceUUID(): String
    fun killAppProcess()
}

