package com.psbapp.networking.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
open class BaseRequest(
    @SerialName("device_token")
    var deviceToken: String = "ENTER_DEVICE_TOKEN",

    @SerialName("device_type")
    var deviceType: String = "android",
)