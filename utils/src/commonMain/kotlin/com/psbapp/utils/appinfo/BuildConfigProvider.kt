package com.psbapp.utils.appinfo

interface BuildConfigProvider {
    val versionName: String
    val versionCode: Int
}