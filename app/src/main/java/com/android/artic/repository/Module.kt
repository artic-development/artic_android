package com.android.artic.repository

import com.android.artic.repository.local.LocalDataSource
import com.android.artic.repository.local.MockLocalDataSource
import com.android.artic.repository.remote.RemoteDataSource
import com.android.artic.repository.remote.ArticApi
import org.koin.dsl.module

val module = module {
    // design singleton for same accessibility in every where in this app
    single { ArticRepository(get(), get(), get()) }

    single { ArticApi() as RemoteDataSource }
    single { MockLocalDataSource() as LocalDataSource }
}