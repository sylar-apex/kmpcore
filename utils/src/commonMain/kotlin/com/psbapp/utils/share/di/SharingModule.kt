package com.psbapp.utils.share.di

import com.psbapp.utils.share.SharingFactory
import org.koin.dsl.module

fun sharingModule() = module {
    factory { SharingFactory }
}