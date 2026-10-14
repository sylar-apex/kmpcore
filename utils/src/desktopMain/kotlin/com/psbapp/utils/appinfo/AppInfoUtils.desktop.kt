package com.psbapp.utils.appinfo

import com.psbapp.utils.EMPTY
import org.koin.core.component.KoinComponent
import java.io.File
import java.util.Properties
import java.util.UUID

private fun readOrCreateDeviceId(): String {
    val file = File(System.getProperty("user.home"), ".paynxt/device_id.properties")
    val props = Properties()
    if (file.exists()) {
        file.inputStream().use { props.load(it) }
        props.getProperty("device_id")?.let { return it }
    }
    val newId = UUID.randomUUID().toString()
    props.setProperty("device_id", newId)
    file.parentFile?.mkdirs()
    file.outputStream().use { props.store(it, null) }
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