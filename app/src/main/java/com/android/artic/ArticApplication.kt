package com.android.artic

import android.app.Application
import com.android.artic.repository.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArticApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ArticApplication)
            modules(listOf(
                module
            ))
        }
    }
}