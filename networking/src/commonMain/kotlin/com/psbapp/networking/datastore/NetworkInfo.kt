package com.psbapp.networking.datastore

interface NetworkInfo {
    suspend fun getAccessToken(): String
    fun getBaseUrl(): String
    fun getSocketHostUrl(): String
    fun getSocketPort(): Int

    /**
     * Whether the socket handshake uses TLS (`wss://`). Defaults to false so existing apps keep
     * their plain-`ws` dev behaviour; override it to true wherever the API is served over HTTPS,
     * because a browser refuses a `ws://` socket opened from an `https://` page.
     */
    fun isSocketSecure(): Boolean = false
    fun getXApiKey(): String
    fun getAppVersion(): String
}