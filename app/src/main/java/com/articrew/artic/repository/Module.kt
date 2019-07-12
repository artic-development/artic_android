package com.articrew.artic.repository

import com.articrew.artic.repository.local.LocalDataSource
import com.articrew.artic.repository.local.MockLocalDataSource
import com.articrew.artic.repository.remote.RemoteDataSource
import com.articrew.artic.repository.remote.RetrofitDataSource
import org.koin.dsl.module

val module = module {
    // design singleton for same accessibility in every where in this app
    single { ArticRepository(get(), get(), get()) }

    single { RetrofitDataSource(get()) as RemoteDataSource }
    single { MockLocalDataSource() as LocalDataSource }
}