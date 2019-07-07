package com.android.artic

import android.app.Application
import com.android.artic.auth.apiModule
import com.android.artic.logger.loggerModule
import com.android.artic.repository.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArticApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // 의존성 주입을 위해 startKoin 을 호출해준다.
        startKoin {
            androidContext(this@ArticApplication)
            modules(listOf(
                module,
                loggerModule,
                apiModule
            ))
        }
    }
}