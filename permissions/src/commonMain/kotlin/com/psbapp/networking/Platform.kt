package com.psbapp.networking

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform