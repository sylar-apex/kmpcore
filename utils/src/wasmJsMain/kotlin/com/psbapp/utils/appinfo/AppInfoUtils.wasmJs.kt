package com.psbapp.utils.appinfo

import com.psbapp.utils.EMPTY
import kotlinx.browser.window
import org.koin.core.component.KoinComponent
import kotlin.random.Random

private const val DEVICE_ID_KEY = "paynxt_device_id"

private fun randomId(): String {
    val chars = "0123456789abcdef"
    return (1..32).map { chars[Random.nextInt(chars.length)] }.joinToString("")
}

private fun readOrCreateDeviceId(): String {
    val storage = window.localStorage
    storage.getItem(DEVICE_ID_KEY)?.let { return it }
    val newId = randomId()
    storage.setItem(DEVICE_ID_KEY, newId)
    return newId
}

actual object AppInfoUtils : KoinComponent {

    actual fun getAppVersionName(): String {
        return "Desktop"
    }

    actual fun getAppVersionCode(): String {
        return "Desktop"
    }

    actual fun getDeviceManufacturer(): String {
        return "APPLE"
    }

    actual fun getDeviceModel(): String {
        return "UIDevice.currentDevice.model"
    }

    actual fun getOSVersion(): String {
        return "UIDevice.currentDevice.systemVersion"
    }

    actual fun getPackageName(): String {
        return "Unknown"
    }

    actual fun getNetwork(): String {
        return String.EMPTY
    }

    actual fun getNetworkCarrier(): String {

        return "Unknown"
    }

    actual fun getDeviceUUID(): String {
        return readOrCreateDeviceId()
    }


    actual fun killAppProcess() {

    }


}