package com.psbapp.networking.utils

object KtorConstants {

    object NetworkKey {
        val X_API_KEY =
                "07i43rUQDqlg6hvl4ifubhOluwDNTwJde8GSJGhvZuxPG9WBouNOgaYJLK5iuVDwQlPNmv5oDiQaVLhZSTJGp4ZkD3Yzg6iExugux63rUTxYAEVrxBG8393UOTConHrU"
        val X_APP_VERSION = "v1"
    }

    object NetworkErrorCodes {
        const val ACCESS_TOKEN_EXPIRED = 401
        const val REFRESH_TOKEN_EXPIRED = 403
        const val INTERNET_NOT_WORKING = 712
        const val UNKNOWN_ERROR_OCCURRED = 713
        const val NETWORK_CALL_CANCELLED = 714
        const val DATA_SERIALIZATION_ERROR = 715
    }

    object NetworkErrorMessages {
        const val SOME_ERROR_OCCURRED = "Some error occurred"
        const val ACCESS_TOKEN_EXPIRED = ""
        const val PLEASE_LOGIN_AGAIN = "Please login again"
        const val APP_UNDER_MAINTENANCE = "App under maintenance"
        const val PLEASE_CHECK_YOUR_INTERNET_CONNECTION = "Please check your internet connection"
        const val DATA_SERIALIZATION_ERROR = "Data serialization error"
    }

    internal object NetworkApiConfig {
        const val DEFAULT_LOG_TAG = "##NETWORK_LOG##"

        const val SOCKET_TIMEOUT_MILLIS = 60_000L
        const val CONNECT_TIMEOUT_MILLIS = 60_000L
        const val REQUEST_TIMEOUT_MILLIS = 60_000L
        const val NETWORK_TIMEOUT_DURATION = 60_000L
        const val NETWORK_RETRY = 3
    }

    internal object AuthHeader {
        const val AUTH_HEADER_KEY = "Authorization"
        const val X_API_KEY = "x-api-key"
        const val ACCEPT_LANGUAGE = "Accept-Language"
        const val X_APP_VERSION = "x-app-version"
        const val BEARER = "Bearer "
        const val DEVICE_MAKE = "DEVICE_MAKE"
        const val DEVICE_MODEL = "DEVICE_MODEL"
        const val DEVICE_TYPE_VALUE = "MOBILE"

        const val DEVICE_META = "device-meta"
        const val CLIENT_VERSION = "client-version"
        const val APPLICATION_JSON = "application/json"
        const val REFERRER_URL = "https://android.g24"
        const val CLIENT_TYPE = "client-type"
        const val OS = "os"

    }

    internal object DeviceMetaData {
        const val DEVICE_MAKE = "DEVICE_MAKE"
        const val DEVICE_MODEL = "DEVICE_MODEL"
        const val DEVICE_TYPE_VALUE = "MOBILE"
        const val OS_VERSION = "OS_VERSION"
        const val APP_VERSION = "APP_VERSION"
        const val PACKAGE_NAME = "PACKAGE_NAME"
        const val NETWORK = "network"
        const val CARRIER = "carrier"
    }
}