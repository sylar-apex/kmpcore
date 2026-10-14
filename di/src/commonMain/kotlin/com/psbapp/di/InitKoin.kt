package com.psbapp.di

import org.koin.core.context.startKoin

fun initKoin(koinModulesInterface: KoinModulesInterface) {
    startKoin { modules(koinModulesInterface.getModules()) }
}