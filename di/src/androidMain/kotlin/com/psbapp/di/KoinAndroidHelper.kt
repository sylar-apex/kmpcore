package com.psbapp.di

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(application: Application, koinModulesInterface: KoinModulesInterface) {
    startKoin {
        androidContext(application.applicationContext)
        modules(koinModulesInterface.getModules() + contextModule(application))
    }
}

/**
 * Creation of context module will help to inject android context outside of any scope.
 */
fun contextModule(application: Application) = module {
    single<Context> { application.applicationContext }
}