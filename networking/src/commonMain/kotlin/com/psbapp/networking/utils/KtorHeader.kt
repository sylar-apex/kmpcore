package com.psbapp.networking.utils

import com.psbapp.networking.utils.KtorConstants.AuthHeader.APPLICATION_JSON
import com.psbapp.networking.utils.KtorConstants.AuthHeader.CLIENT_TYPE
import com.psbapp.networking.utils.KtorConstants.AuthHeader.CLIENT_VERSION
import com.psbapp.networking.utils.KtorConstants.AuthHeader.DEVICE_MAKE
import com.psbapp.networking.utils.KtorConstants.AuthHeader.DEVICE_META
import com.psbapp.networking.utils.KtorConstants.AuthHeader.DEVICE_MODEL
import com.psbapp.networking.utils.KtorConstants.AuthHeader.DEVICE_TYPE_VALUE
import com.psbapp.networking.utils.KtorConstants.DeviceMetaData.APP_VERSION
import com.psbapp.networking.utils.KtorConstants.DeviceMetaData.CARRIER
import com.psbapp.networking.utils.KtorConstants.DeviceMetaData.NETWORK
import com.psbapp.networking.utils.KtorConstants.DeviceMetaData.OS_VERSION
import com.psbapp.networking.utils.KtorConstants.DeviceMetaData.PACKAGE_NAME
import com.psbapp.utils.appinfo.AppInfoUtils
import com.psbapp.utils.getPlatform
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive


fun HeadersBuilder.apiHeaders(block: HeadersBuilder.() -> Unit = {}) {
    append(
        HttpHeaders.Accept,
        APPLICATION_JSON
    )
    append(
        HttpHeaders.ContentType,
        APPLICATION_JSON
    )
    append(
        HttpHeaders.Referrer,
        AppInfoUtils.getPackageName()
    )
    append(
        HttpHeaders.UserAgent,
        getPlatform().type.name
    )
    append(
        DEVICE_META,
        deviceMetaData
    )
    append(
        CLIENT_TYPE,
        DEVICE_TYPE_VALUE
    )
    append(
        CLIENT_VERSION,
        AppInfoUtils.getAppVersionCode()
    )
    block()
}


private val deviceMetaData = JsonObject(
    mapOf<String, JsonElement>(
        APP_VERSION to JsonPrimitive(AppInfoUtils.getAppVersionCode()),
        DEVICE_MAKE to JsonPrimitive(AppInfoUtils.getDeviceManufacturer()),
        DEVICE_MODEL to JsonPrimitive(AppInfoUtils.getDeviceModel()),
        OS_VERSION to JsonPrimitive(AppInfoUtils.getOSVersion()),
        PACKAGE_NAME to JsonPrimitive(AppInfoUtils.getPackageName()),
        NETWORK to JsonPrimitive(AppInfoUtils.getNetwork()),
        CARRIER to JsonPrimitive(AppInfoUtils.getNetworkCarrier())
    )
).toString()