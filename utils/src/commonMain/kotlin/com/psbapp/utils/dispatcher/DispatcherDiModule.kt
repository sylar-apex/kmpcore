package com.psbapp.utils.dispatcher

import org.koin.dsl.module

fun dispatcherDiModule() = module {
    single<DispatcherProvider> {
        DispatcherProviderImpl()
    }
}