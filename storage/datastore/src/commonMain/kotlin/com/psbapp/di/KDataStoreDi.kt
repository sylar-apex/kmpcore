package com.psbapp.di

import com.psbapp.datastore.KDataStorePref
import com.psbapp.datastore.cache.KCache
import org.koin.dsl.module

fun kDataStoreModule() = module {
    single<KDataStorePref> { KDataStorePref("sarthiteacher.preferences_pb") }
    single { KCache(get()) }
}