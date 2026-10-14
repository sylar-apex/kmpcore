package com.psbapp.utils.appinfo

import com.psbapp.utils.EMPTY
import kotlinx.browser.window
import kotlin.random.Random

private const val DEVICE_ID_KEY = "paynxt_device_id"

private fun randomId(): String {
    val chars = "0123456789abcdef"
    return (1..32).map { chars[Random.nextInt(chars.length)] }.joinToString("")
}

/** The browser has no device id, so one is minted once and kept in local storage. */
private fun readOrCreateDeviceId(): String {
    val storage = window.localStorage
    storage.getItem(DEVICE_ID_KEY)?.let { return it }
    val newId = randomId()
    storage.setItem(DEVICE_ID_KEY, newId)
    return newId
}

actual object AppInfoUtils {

    actual fun getAppVersionName(): String = "Web"

    actual fun getAppVersionCode(): String = "Web"

    actual fun getDeviceManufacturer(): String = "Unknown"

    actual fun getDeviceModel(): String = window.navigator.platform

    actual fun getOSVersion(): String = window.navigator.userAgent

    actual fun getPackageName(): String = window.location.host

    actual fun getNetwork(): String = String.EMPTY

    actual fun getNetworkCarrier(): String = "Unknown"

    actual fun getDeviceUUID(): String = readOrCreateDeviceId()

    /** A browser tab cannot kill its own process; closing is the closest equivalent. */
    actual fun killAppProcess() {
        window.close()
    }
}
