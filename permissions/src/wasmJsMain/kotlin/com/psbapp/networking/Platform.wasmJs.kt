package com.psbapp.networking

class WasmJsPlatform : Platform {
    override val name: String = "WasmJs"
}

actual fun getPlatform(): Platform = WasmJsPlatform()
