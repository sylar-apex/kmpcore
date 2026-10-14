package com.psbapp.di

import org.koin.core.module.Module

/**
 *  Extend from this interface to create modules in the project.
 */
interface KoinModulesInterface {
    fun getModules(): List<Module>
}