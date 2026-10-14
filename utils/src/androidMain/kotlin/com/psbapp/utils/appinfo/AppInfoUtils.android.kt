package com.psbapp.utils.appinfo

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.psbapp.utils.EMPTY
import com.psbapp.utils.capitalizeFirstChar
import com.psbapp.utils.value
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


actual object AppInfoUtils : KoinComponent {

    private var buildConfigProvider: BuildConfigProvider? = null
    private var currentVersion = String.EMPTY
    private const val UNDEFINED_NETWORK_TYPE = "UNDEFINED"
    private val context: Context by inject<Context>()

    fun setBuildConfigProvider(provider: BuildConfigProvider) {
        buildConfigProvider = provider
    }

    actual fun getAppVersionName(): String {
        return buildConfigProvider?.versionName.value
    }

    actual fun getAppVersionCode(): String {
        return buildConfigProvider?.versionCode.toString()

    }

    actual fun getDeviceManufacturer(): String {
        val manufacturer = Build.MANUFACTURER
        return manufacturer.capitalizeFirstChar()
    }

    actual fun getDeviceModel(): String {
        val model = Build.MODEL
        return model.capitalizeFirstChar()
    }

    actual fun getOSVersion(): String {
        return Build.VERSION.SDK_INT.toString()
    }

    actual fun getPackageName(): String {
        return context.packageName
    }

    actual fun getNetwork(): String {
        return String.EMPTY
    }

    actual fun getNetworkCarrier(): String {
        try {
            val telephonyManager =
                    context.applicationContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return if (telephonyManager.networkOperatorName.isValidHeaderValue()) telephonyManager.networkOperatorName
            else UNDEFINED_NETWORK_TYPE
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return String.EMPTY
    }

    @SuppressLint("HardwareIds")
    actual fun getDeviceUUID(): String {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    /**
     * Calling this method will kill application process
     */
    actual fun killAppProcess() {

        // Get the activity manager
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the list of running processes
        val processInfos: List<RunningAppProcessInfo>? = activityManager.runningAppProcesses

        processInfos?.forEach { processInfo ->
            // Check if the process belongs to your application
            if (processInfo.processName == context.packageName) {
                // Kill the process
                android.os.Process.killProcess(processInfo.pid)
                return
            }
        }
    }

    private fun String.isValidHeaderValue(): Boolean {
        val regex = Regex("[\u0020-\u007e]+|[\t]+")
        return this.matches(regex)
    }


}