package com.articrew.artic.auth

import org.koin.dsl.module

val apiModule = module {
    single { com.articrew.artic.auth.Auth(get(), get()) }
}