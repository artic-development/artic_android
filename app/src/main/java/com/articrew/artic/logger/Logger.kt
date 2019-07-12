package com.articrew.artic.logger

interface Logger {
    fun log(msg: String)
    fun error(msg: String)
    fun info(msg: String)
}