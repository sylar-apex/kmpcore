package com.psbapp.utils.uuid

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun getUUID(): String {
    return Uuid.random().toString()
}
