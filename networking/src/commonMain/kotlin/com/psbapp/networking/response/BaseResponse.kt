package com.psbapp.networking.response

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
open class BaseResponse<T>(
    @SerialName("success")
    @JsonNames(
        "success",
        "status"
    )
    var status: String = "",

    @SerialName("message")
    @JsonNames(
        "error",
        "error_message",
        "message"
    )
    var message: String = "",

    @SerialName("data")
    @JsonNames("data")
    var data: T? = null,
)