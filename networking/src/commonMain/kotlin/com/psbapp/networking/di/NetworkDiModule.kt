package com.psbapp.networking.di

import com.psbapp.networking.client.KtorClient
import com.psbapp.networking.client.NetworkClient
import org.koin.dsl.module

fun networkModule() = module {
    single {
        KtorClient(
            get(),
            get()
        )
    }
    single { NetworkClient(get()) }
}