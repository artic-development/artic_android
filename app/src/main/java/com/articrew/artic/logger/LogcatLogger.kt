package com.articrew.artic.logger

import android.util.Log

class LogcatLogger (
    val name: String
): Logger {
    override fun log(msg: String) { Log.d(name, msg) }
    override fun error(msg: String) {Log.e(name, msg)}
    override fun info(msg: String) {Log.i(name, msg)}
}