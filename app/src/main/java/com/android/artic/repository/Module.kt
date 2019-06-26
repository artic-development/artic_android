package com.android.artic.repository

import org.koin.dsl.module

val module = module {
    // design singleton for same accessibility in every where in this app
    single { ArticRepository() }
}