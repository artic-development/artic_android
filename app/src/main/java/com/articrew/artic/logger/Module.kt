package com.articrew.artic.logger

import org.koin.dsl.module

val loggerModule = module {
    single {  LogcatLogger("ArticApp") as Logger }
}