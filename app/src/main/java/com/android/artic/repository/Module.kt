package com.android.artic.repository

import org.koin.dsl.module

val module = module {
    // singleton 으로 앱 전체에서 동일한 repository 에 접근할 수 있도록 제작
    single { ArticRepository() }
}