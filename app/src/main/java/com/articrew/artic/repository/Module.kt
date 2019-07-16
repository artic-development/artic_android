package com.articrew.artic.repository

import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.local.MockLocalDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import com.articrew.artic.repository.scheduler.AndroidSchduler
import com.articrew.artic.repository.scheduler.ArticSchduler
import org.koin.dsl.module

val module = module {
    // design singleton for same accessibility in every where in this app
    single { ArticRepository(get(), get(), get(),get()) }

    single { AndroidSchduler() as ArticSchduler }
    single { RetrofitDataSource(get()) }
    single { MockLocalDataSource() as LocalDataSource }
}