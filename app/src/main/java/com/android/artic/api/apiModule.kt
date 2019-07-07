package com.android.artic.api

import org.koin.dsl.module

val apiModule = module {
    single { ArticApi(get()) }
}