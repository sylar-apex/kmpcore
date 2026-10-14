package com.psbapp.utils.appinfo


import com.psbapp.utils.EMPTY
import com.psbapp.utils.value
import org.koin.core.component.KoinComponent
import platform.CoreTelephony.CTCarrier
import platform.CoreTelephony.CTTelephonyNetworkInfo
import platform.Foundation.NSBundle
import platform.UIKit.UIDevice


const val APPLE = "Apple"

actual object AppInfoUtils : KoinComponent {

    actual fun getAppVersionName(): String {
        val appVersion = NSBundle.mainBundle.infoDictionary
            ?.getValue("CFBundleShortVersionString") as? String
        return appVersion.value
    }

    actual fun getAppVersionCode(): String {
        val appVersionCode = NSBundle.mainBundle.infoDictionary
            ?.getValue("CFBundleVersion") as? String
        return appVersionCode.value
    }

    actual fun getDeviceManufacturer(): String {
        return APPLE
    }

    actual fun getDeviceModel(): String {
        return UIDevice.currentDevice.model
    }

    actual fun getOSVersion(): String {
        return UIDevice.currentDevice.systemVersion
    }

    actual fun getPackageName(): String {
        return NSBundle.mainBundle.bundleIdentifier ?: "Unknown"
    }

    actual fun getNetwork(): String {
        return String.EMPTY
    }

    actual fun getNetworkCarrier(): String {
        val networkInfo = CTTelephonyNetworkInfo()
        val carrier: CTCarrier? = networkInfo.subscriberCellularProvider
        return carrier?.carrierName ?: "Unknown"
    }

    actual fun getDeviceUUID(): String {
        return UIDevice.currentDevice.identifierForVendor?.UUIDString.value
    }


    actual fun killAppProcess() {

    }


}