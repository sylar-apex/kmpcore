package com.psbapp.networking.client

import io.ktor.client.engine.darwin.Darwin

actual fun getHttpClientEngine() = Darwin.create()