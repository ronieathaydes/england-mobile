package com.england

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun startKoin() = startKoin {}

fun startKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(sharedModule)
}

val sharedModule = module {
    factory { FieldService() }
}
