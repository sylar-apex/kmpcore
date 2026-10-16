package com.psbapp.networking.client

import io.ktor.client.engine.okhttp.OkHttp

actual fun getHttpClientEngine() = OkHttp.create()