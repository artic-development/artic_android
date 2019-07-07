package com.android.artic.logger

interface Logger {
    fun log(msg: String)
    fun error(msg: String)
    fun info(msg: String)
}