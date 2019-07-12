package com.articrew.artic

import android.app.Application
import com.articrew.artic.auth.apiModule
import com.articrew.artic.auth.kakao.KakaoSDKAdapter
import com.articrew.artic.logger.loggerModule
import com.articrew.artic.repository.module
import com.kakao.auth.KakaoSDK
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

        // kakao init
        //KakaoSDK.init(KakaoSDKAdapter(this))
    }
}